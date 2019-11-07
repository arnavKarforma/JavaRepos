import java.util.Arrays; 
class sfgsd {
    public static int solution(int[] ranks) {
       if(ranks.length ==0){
           return 0;
       }
       Arrays.sort(ranks);
       int reports = 0;
       int totalReports= 0; 
       for(int i =0; i < ranks.length-1; i ++){
    	   if(ranks[i]+1 == ranks[i+1]){
               totalReports+= reports; 
               reports = 1;
            }
    	   if(ranks[i] == ranks[i+1]) {
    		   reports++;
    		   continue;
    	   }
           
           else if(ranks[i]+1 < ranks[i+1]){
            reports =  1;   
           }
        
       }
       return totalReports;
    }
    public static void main(String[] args) {
    	int arr[] =new int[] {3, 4, 3, 0, 2, 2, 3, 0, 0};
    	System.out.println(solution(arr));
	}
	
}