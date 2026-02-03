const todoInput = document.querySelector('#todo-input');
const addButton = document.querySelector('#add-btn');
const todoList = document.querySelector('#todo-list');
const message = document.querySelector('#message');
const prioritySelect = document.querySelector('#priority');
const dueInput = document.querySelector('#due');
const counter = document.querySelector('#counter');
const sortSelect = document.querySelector('#sort');
const clearCompletedButton = document.querySelector('#clear-completed');
const clearOverdueButton = document.querySelector('#clear-overdue');
const clearAllButton = document.querySelector('#clear-all');
const themeToggleButton = document.querySelector('#theme-toggle');

const filterAllButton = document.querySelector('#filter-all');
const filterActiveButton = document.querySelector('#filter-active');
const filterCompletedButton = document.querySelector('#filter-completed');

const STORAGE_KEY = 'todo_app_v2';
const THEME_KEY = 'todo_theme';

/** @typedef {{ id: string, text: string, completed: boolean, priority: 'low'|'medium'|'high', due: string|null, createdAt: number, order: number }} Todo */

/** @type {Todo[]} */
let todos = [];
let currentFilter = 'all'; // all | active | completed
let currentSort = 'manual'; // manual | newest | oldest | priority | due
let draggingId = null;

function makeId() {
    if (typeof crypto !== 'undefined' && typeof crypto.randomUUID === 'function') {
        return crypto.randomUUID();
    }
    return String(Date.now()) + '-' + String(Math.random()).slice(2);
}

function normalizeText(text) {
    return text.trim().replace(/\s+/g, ' ').toLowerCase();
}

function priorityRank(priority) {
    if (priority === 'high') return 0;
    if (priority === 'medium') return 1;
    return 2;
}

function todayIsoLocal() {
    const now = new Date();
    const local = new Date(now.getTime() - now.getTimezoneOffset() * 60000);
    return local.toISOString().slice(0, 10);
}

function setMessage(text, type = 'info') {
    message.textContent = text;
    message.dataset.type = type;

    if (!text) return;
    window.clearTimeout(setMessage._timer);
    setMessage._timer = window.setTimeout(() => {
        message.textContent = '';
        message.dataset.type = '';
    }, 2000);
}

function save() {
    const payload = {
        todos,
        currentFilter,
        currentSort,
    };
    try {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(payload));
    } catch {
        // ignore
    }
}

function load() {
    try {
        const raw = localStorage.getItem(STORAGE_KEY);
        if (!raw) return;
        const parsed = JSON.parse(raw);
        if (Array.isArray(parsed?.todos)) todos = parsed.todos;
        if (typeof parsed?.currentFilter === 'string') currentFilter = parsed.currentFilter;
        if (typeof parsed?.currentSort === 'string') currentSort = parsed.currentSort;
    } catch {
        // ignore
    }
}

function setTheme(theme) {
    const isDark = theme === 'dark';
    document.body.classList.toggle('dark', isDark);
    themeToggleButton.setAttribute('aria-pressed', String(isDark));
    themeToggleButton.textContent = isDark ? 'Light' : 'Dark';
    try {
        localStorage.setItem(THEME_KEY, theme);
    } catch {
        // ignore
    }
}

function loadTheme() {
    try {
        const theme = localStorage.getItem(THEME_KEY);
        if (theme === 'dark' || theme === 'light') {
            setTheme(theme);
            return;
        }
    } catch {
        // ignore
    }
    setTheme('light');
}

function setFilter(filter) {
    currentFilter = filter;
    for (const btn of [filterAllButton, filterActiveButton, filterCompletedButton]) {
        btn.classList.toggle('active', btn.dataset.filter === filter);
    }
    save();
    render();
}

function applyFilter(list) {
    if (currentFilter === 'active') return list.filter((t) => !t.completed);
    if (currentFilter === 'completed') return list.filter((t) => t.completed);
    return list;
}


function applySort(list) {
    const copy = [...list];

    if (currentSort === 'newest') {
        copy.sort((a, b) => b.createdAt - a.createdAt);
        return copy;
    }
    if (currentSort === 'oldest') {
        copy.sort((a, b) => a.createdAt - b.createdAt);
        return copy;
    }
    if (currentSort === 'priority') {
        copy.sort((a, b) => {
            const pr = priorityRank(a.priority) - priorityRank(b.priority);
            if (pr !== 0) return pr;
            return a.order - b.order;
        });
        return copy;
    }
    if (currentSort === 'due') {
        copy.sort((a, b) => {
            const aHas = Boolean(a.due);
            const bHas = Boolean(b.due);
            if (aHas !== bHas) return aHas ? -1 : 1;
            if (a.due && b.due && a.due !== b.due) return a.due.localeCompare(b.due);
            return a.order - b.order;
        });
        return copy;
    }

    // manual
    copy.sort((a, b) => a.order - b.order);
    return copy;
}

function updateCounter() {
    const total = todos.length;
    const completed = todos.filter((t) => t.completed).length;
    const active = total - completed;
    counter.textContent = `${active} active / ${total} total`;
}

function nextOrder() {
    let maxOrder = 0;
    for (const t of todos) maxOrder = Math.max(maxOrder, t.order ?? 0);
    return maxOrder + 1;
}

function addTodo() {
    const raw = todoInput.value;
    const text = raw.trim();
    if (!text) {
        setMessage('Please type a task first', 'error');
        todoInput.focus();
        return;
    }

    const normalized = normalizeText(text);
    const hasDuplicate = todos.some((t) => normalizeText(t.text) === normalized);
    if (hasDuplicate) {
        setMessage('That task already exists', 'error');
        return;
    }

    /** @type {Todo} */
    const todo = {
        id: makeId(),
        text,
        completed: false,
        priority: /** @type {'low'|'medium'|'high'} */ (prioritySelect.value || 'medium'),
        due: dueInput.value ? dueInput.value : null,
        createdAt: Date.now(),
        order: nextOrder(),
    };

    todos.push(todo);
    todoInput.value = '';
    dueInput.value = '';
    prioritySelect.value = 'medium';
    todoInput.focus();

    save();
    render();
    setMessage('Task added', 'success');
}

function startEdit(todoId) {
    const li = todoList.querySelector(`li[data-id="${todoId}"]`);
    if (!li) return;
    if (li.dataset.editing === 'true') return;
    li.dataset.editing = 'true';

    const todo = todos.find((t) => t.id === todoId);
    if (!todo) return;

    const textSpan = li.querySelector('.todo-text');
    if (!textSpan) return;

    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'edit-input';
    input.value = todo.text;
    input.setAttribute('aria-label', 'Edit task');

    textSpan.replaceWith(input);
    input.focus();
    input.setSelectionRange(input.value.length, input.value.length);

    function cleanup(cancelled) {
        li.dataset.editing = 'false';
        const span = document.createElement('span');
        span.className = 'todo-text';
        span.textContent = cancelled ? todo.text : (todos.find((t) => t.id === todoId)?.text ?? todo.text);
        input.replaceWith(span);
    }

    function commit() {
        const newText = input.value.trim();
        if (!newText) {
            setMessage('Task cannot be empty', 'error');
            input.focus();
            return;
        }
        const normalized = normalizeText(newText);
        const hasDuplicate = todos.some((t) => t.id !== todoId && normalizeText(t.text) === normalized);
        if (hasDuplicate) {
            setMessage('That task already exists', 'error');
            input.focus();
            return;
        }
        const t = todos.find((x) => x.id === todoId);
        if (!t) return;
        t.text = newText;
        save();
        cleanup(false);
        render();
        setMessage('Task updated', 'success');
    }

    input.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            commit();
        }
        if (e.key === 'Escape') {
            e.preventDefault();
            cleanup(true);
        }
    });

    input.addEventListener('blur', () => {
        // Keep UX simple: commit on blur if changed, otherwise just cleanup.
        if (input.value.trim() !== todo.text) commit();
        else cleanup(true);
    });
}

function toggleCompleted(todoId) {
    const t = todos.find((x) => x.id === todoId);
    if (!t) return;
    t.completed = !t.completed;
    save();
    render();
}

function deleteTodo(todoId) {
    const before = todos.length;
    todos = todos.filter((t) => t.id !== todoId);
    if (todos.length !== before) {
        save();
        render();
        setMessage('Task deleted', 'success');
    }
}

function setSort(sort) {
    currentSort = sort;
    sortSelect.value = sort;
    save();
    render();
}


function reorderManual(fromId, toId) {
    if (fromId === toId) return;
    if (currentSort !== 'manual') return;

    const ordered = [...todos].sort((a, b) => a.order - b.order);
    const fromIndex = ordered.findIndex((t) => t.id === fromId);
    const toIndex = ordered.findIndex((t) => t.id === toId);
    if (fromIndex < 0 || toIndex < 0) return;

    const [moved] = ordered.splice(fromIndex, 1);
    ordered.splice(toIndex, 0, moved);

    for (let i = 0; i < ordered.length; i++) {
        ordered[i].order = i + 1;
    }

    // write back updated orders
    const orderMap = new Map(ordered.map((t) => [t.id, t.order]));
    for (const t of todos) {
        const o = orderMap.get(t.id);
        if (typeof o === 'number') t.order = o;
    }

    save();
    render();
}

function render() {
    updateCounter();

    const list = applySort(applyFilter(todos));
    todoList.innerHTML = '';
    const today = todayIsoLocal();

    for (const todo of list) {
        const li = document.createElement('li');
        li.className = 'todo-item';
        li.dataset.id = todo.id;
        li.classList.toggle('completed', todo.completed);
        li.classList.toggle('overdue', Boolean(todo.due && !todo.completed && todo.due < today));
        li.classList.toggle('drag-disabled', currentSort !== 'manual');

        li.draggable = currentSort === 'manual';
        if (li.draggable) {
            li.addEventListener('dragstart', () => {
                draggingId = todo.id;
                li.classList.add('dragging');
            });
            li.addEventListener('dragend', () => {
                draggingId = null;
                li.classList.remove('dragging');
            });
            li.addEventListener('dragover', (e) => {
                e.preventDefault();
            });
            li.addEventListener('drop', (e) => {
                e.preventDefault();
                const targetId = li.dataset.id;
                if (draggingId && targetId) reorderManual(draggingId, targetId);
            });
        }

        const left = document.createElement('div');
        left.className = 'todo-left';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = todo.completed;
        checkbox.className = 'complete-checkbox';
        checkbox.setAttribute('aria-label', `Mark complete: ${todo.text}`);
        checkbox.addEventListener('change', () => toggleCompleted(todo.id));

        const text = document.createElement('span');
        text.className = 'todo-text';
        text.textContent = todo.text;

        const meta = document.createElement('div');
        meta.className = 'todo-meta';

        const badge = document.createElement('span');
        badge.className = `badge badge-${todo.priority}`;
        badge.textContent = todo.priority;

        meta.appendChild(badge);
        if (todo.due) {
            const due = document.createElement('span');
            due.className = 'due';
            due.textContent = `Due: ${todo.due}`;
            meta.appendChild(due);
        }

        left.append(checkbox, text, meta);

        const actions = document.createElement('div');
        actions.className = 'todo-actions';

        const edit = document.createElement('button');
        edit.type = 'button';
        edit.className = 'secondary small';
        edit.textContent = 'Edit';
        edit.addEventListener('click', () => startEdit(todo.id));

        const del = document.createElement('button');
        del.type = 'button';
        del.className = 'delete-btn small';
        del.textContent = 'Delete';
        del.setAttribute('aria-label', `Delete task: ${todo.text}`);
        del.addEventListener('click', () => deleteTodo(todo.id));

        actions.append(edit, del);

        li.append(left, actions);
        todoList.appendChild(li);
    }

    // reflect UI state
    setFilter(currentFilter);
    sortSelect.value = currentSort;
}

// Events
addButton.addEventListener('click', addTodo);
todoInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        addTodo();
    }
});

filterAllButton.addEventListener('click', () => setFilter('all'));
filterActiveButton.addEventListener('click', () => setFilter('active'));
filterCompletedButton.addEventListener('click', () => setFilter('completed'));

sortSelect.addEventListener('change', () => setSort(sortSelect.value));

clearCompletedButton.addEventListener('click', () => {
    const before = todos.length;
    todos = todos.filter((t) => !t.completed);
    if (todos.length !== before) {
        save();
        render();
        setMessage('Completed tasks cleared', 'success');
    } else {
        setMessage('No completed tasks', 'info');
    }
});

clearOverdueButton.addEventListener('click', () => {
    const today = todayIsoLocal();
    const before = todos.length;
    todos = todos.filter((t) => !(t.due && !t.completed && t.due < today));
    const removed = before - todos.length;
    if (removed > 0) {
        save();
        render();
        setMessage(`Cleared ${removed} overdue task${removed === 1 ? '' : 's'}`, 'success');
    } else {
        setMessage('No overdue tasks', 'info');
    }
});

clearAllButton.addEventListener('click', () => {
    todos = [];
    save();
    render();
    setMessage('All tasks cleared', 'success');
});

themeToggleButton.addEventListener('click', () => {
    const isDark = document.body.classList.contains('dark');
    setTheme(isDark ? 'light' : 'dark');
});

// Init
loadTheme();
load();
sortSelect.value = currentSort;
setFilter(currentFilter);
render();
