public class practice {
    public static String toUpperCase(String str){
        StringBuilder sb=new StringBuilder("");
        char ch=Character.toUpperCase(str.charAt(0));
        sb.append(ch);
        for(int i=1;i<str.length();i++){
            if(str.charAt(i)==' ' && i<str.length()-1){
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            }else{
                sb.append(str.charAt(i));
            } 

        }
        return sb.toString();


       
    }
    public static String compress(String str1){
        StringBuilder sb=new StringBuilder("");
        for(int i=0;i<str1.length();i++){
            Integer count=1;
            while(i<str1.length()-1 && str1.charAt(i)==str1.charAt(i+1)){
                count++;
                i++;
            }
            sb.append(str1.charAt(i));
            if(count>1){
                sb.append(count.toString());
            }
            
        }
            return sb.toString();
    }
    public static void main(String[] args) {
        String str="hi, i am shradha";
        String str1="aaabbcccdd";
       // System.out.println(toUpperCase(str));
        System.out.println(compress(str1));
    }
    
}
