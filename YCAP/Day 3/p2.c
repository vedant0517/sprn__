//173 Vedant
#include<stdio.h>
#include<string.h>
int main()
{
    char s1[20]="silent";
    char s2[20]="listen";
    int freq1[26] = {0}; 
    int freq2[26] = {0};
    int len1, len2, i, isAnagram = 1;
    
    len1 = strlen(s1);
    len2 = strlen(s2);
    
    if(len1 != len2) {
        printf("Not anagrams\n");
        return 0;
    }
    
    for(i = 0; i < len1; i++) {
        freq1[s1[i] - 'a']++;
        freq2[s2[i] - 'a']++;
    }
    
    for(i = 0; i < 26; i++) {
        if(freq1[i] != freq2[i]) {
            isAnagram = 0;
            break;
        }
    }
    
    if(isAnagram)
        printf("%s and %s are anagrams\n", s1, s2);
    else
        printf("%s and %s are not anagrams\n", s1, s2);
    
    return 0;
}