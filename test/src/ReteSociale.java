import java.util.ArrayList;
import java.util.List;

public class ReteSociale {
    static User[] users = new User[10];

    public static void main(String args[]) {

        for (int i = 0; i < 10; i++) {
            users[i] = new User(i);
        }

        users[0].addFriend(users[1]);
        users[1].addFriend(users[0]);

        users[0].addFriend(users[2]);
        users[2].addFriend(users[0]);

        users[1].addFriend(users[5]);
        users[5].addFriend(users[1]);

        users[1].addFriend(users[9]);
        users[9].addFriend(users[1]);

        users[2].addFriend(users[3]);
        users[3].addFriend(users[2]);

        users[2].addFriend(users[4]);
        users[4].addFriend(users[2]);

        List<Long> ff =allFriends(0, 2);
        System.out.println(ff);
    }

    public static List<Long> allFriends(long userId, int maxHops) {
        List<Long> allFriends = new ArrayList<>();
        allFriends.add(userId);

        if(maxHops > 0) {
            for (int i=0; i<users.length; i++) {
                User user = users[i];
                if(user.id == userId) {
                    for (int j=0;j<maxHops; j++) {
                        List<Long> friends = user.friends.stream().
                                filter(f -> !allFriends.contains( f.id )).
                                mapToLong(f -> f.id).
                                collect(ArrayList::new, (l, f) -> l.add(f), ArrayList::addAll );

                        allFriends.addAll(friends);

                        for(int k=0; k< user.friends.size(); k++) {
                            List<Long> ff = allFriends(user.friends.get(k).id, maxHops - 1);
                            ff.removeAll(allFriends);
                            allFriends.addAll(ff);
                        }
                    }
                    break;
                }
            }

        }
        allFriends.remove(userId);
        return allFriends;
    }

}

class User {
    long id;
    List<User> friends;

    User(int id) {
        this.friends = new ArrayList<>();
        this.id = id;
    }

    void addFriend(User friend) {
        friends.add(friend);
    }
}