public interface Network {

	// add a connection between two people in the network. Note that it is 
	// possible that connection of the person's name you are to process is not 
	// present in the arraylist 'names'
	void addPeople(String personAname,String personBname);
	
	//process a multiline string containing multiple personA - personB connections
	void processConnections(String multiStringWithConnections);
	
	
	// return true if there is a link of friends between two people
	boolean areConnected(String personAname,String personBname);
	
	
	// main function which, given a name, will return array of people which are
	// connected to it via link of its friends
	String[] inviteToParty(String name);
	
	
}