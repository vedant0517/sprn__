 Queue q=new Queue(5);
        q.add(1);
        q.add(4);
        q.add(6);

        while(!q.isEmpty())
        {
            System.out.println(q.peek());
            q.remove();
        }