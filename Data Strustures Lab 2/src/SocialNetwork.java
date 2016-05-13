import java.util.ArrayList;
public class SocialNetwork implements Network{
    //maximum number of people in the network
    int n;
    // names of the people in the network
    ArrayList<String> names;
    
    ArrayList<String>[] community = new ArrayList[30]; // array list for communities   
    
    String[] people;
    String[] pairs;
    String[] member;
    
    // constructor
    public SocialNetwork(int n_) {
        this.n=n_; 
    }
    public void isInConnection(String personBname, int groupNumber){
       int l=0;
        for (int j=groupNumber+1; j<community.length; j++) {
            if(community[j].contains(personBname)){
            l=j;
            for (int k=0; k<community[l].size(); k++) {
                if(!community[groupNumber].contains(community[l].get(k))){
                    community[groupNumber].add(community[l].get(k));
                    
                }
             }
             
             
             
             for(int a=l; a<community.length-1; a++){
                 community[a]= community[a+1];
             }     
            
            }
            
        }
        
    }
    
    // Add a friendship between personAname - personBname into the network
    @Override
    public void addPeople(String personAname, String personBname) { 
        // loop going through lists
        for (int j=0; j<community.length; j++) {
            // checking if community is empty  
            //then we can add names
            if (community[j].isEmpty()){
                   community[j].add(personAname); 
                   community[j].add(personBname); 
                   break;
                   }
            //adding person B in case that person A is in same community with other members
                if (community[j].contains(personAname) && !(community[j].contains(personBname)) ) {
                    
                    community[j].add(personBname);
                    isInConnection(personBname, j);
                     break;
                     }
              //adding person A in case that person B is in same community with other members
                    else if (!(community[j].contains(personAname)) && (community[j].contains(personBname)) ) {
                        community[j].add(personAname);
                        isInConnection(personAname, j);
                        break; 
                       }
                // in case that person A and B are already in that community, then we DON'T add them again
                    else if ((community[j].contains(personAname)) && (community[j].contains(personBname)) ) {
                        
                        break;
                       }
      
                 } 
      
        
    }
    // process multiple friendship requests from a multiline string where
    // each line is in the form: personA - personB
    // Hint: this function should be using addPeople()
    
    @Override
    public void processConnections(String multiStringWithConnections) {
        // splitting people on pairs
         pairs = multiStringWithConnections.split("[\\n]");
       
           for(int i=0;i<community.length;i++) {
               community[i] = new ArrayList<String>();
           }
        
           for(int i=0; i<pairs.length; i++){
               member = pairs[i].split("[-]"); // splitting names 
               
                  
               addPeople(member[0].trim(),member[1].trim() ); 
           }
           
       }
      
    
    @Override
    public String[] inviteToParty(String name) {
        String[] friends = null;
       int numberCom=0;
     
        for(int i=0; i<community.length; i++){
            if(community[i].contains(name) ){
                friends= new String[community[i].size() - 1];
                numberCom=i;
            }
            
            
        }
        int s=0;
        for(int j=0; j<friends.length;  ){
            if(!community[numberCom].get(s).equals(name)){
            friends[j]=community[numberCom].get(s);
            j++;
            
        }
            s++;
            }
     return friends;
    }
    
    @Override
    public boolean areConnected(String personAname, String personBname) {
        
        if(personAname.contains(personBname)){
            return true;
        }else{
        return false;
        }
    }
  
    public String printCommunities() {
        String commstring = "";
        
        for (int j=0;j<community.length;j++){
            if (community[j].isEmpty()) {
                break;
                }
            
            commstring = commstring +"Community #"+(j+1);
            
          
            for(String k : community[j])
                {
                
                commstring = commstring+ " "+ k;
                
                }
                commstring = commstring + "\n"; // adds a newline to return string
        
        }
        return commstring;
    }
}