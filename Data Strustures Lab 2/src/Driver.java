
public class Driver {
	
	
	public static void main(String[] args) {
		
		int n=30;
		SocialNetwork social = new SocialNetwork(n);
		
		String connections="Mary - John\n"
				+ "Josh - Mary\n"
				+ "Kate - Ivan\n"
				+ "Isaac - Whitney\n"
				+ "Josh - Howard\n"
				+ "Andy - Howard\n"
				+ "Andy - Mary";
		

		
		// print out connection list
		System.out.println(connections+"\n");
		
		// process friendship requests
		social.processConnections(connections);
		
		System.out.println(social.printCommunities());
		
		
		String[] friends=social.inviteToParty("Mary");
		System.out.print("Friends to be invited to the party: ");
		for (int i = 0; i < friends.length; i++) {
		System.out.print(friends[i]+" ");
		}
		System.out.println("\n");
		// add some more connections. Notice that we add people which haven’t been seen previously.
		social.addPeople("Steven", "Jim");
		social.addPeople("Ivan", "Isaac");
		System.out.println(social.printCommunities());
		social.addPeople("Mary", "Kate");
		System.out.println(social.printCommunities());
		}
		}
