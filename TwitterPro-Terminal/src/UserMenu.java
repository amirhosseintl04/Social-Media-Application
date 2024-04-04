import java.util.Objects;
import java.util.Scanner;

public class UserMenu {

    public void menu(User user, User[] users){
        Scanner inputs = new Scanner(System.in);
        String input;
        do{
            System.out.print( "\nPlease select a choice!\n1)New post\n2)Posts\n3)Flowers and flowing\n4)Search\n5)Edit profile\n6)Log out\n> ");
            input = inputs.next();
            while (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3")&& !Objects.equals(input, "4")&& !Objects.equals(input, "5") && !Objects.equals(input, "6")) {
                System.out.print( "Your choice is not among the options!\n1)New post\n2)Posts\n3)Flowers and flowing\n4)Search\n5)Edit profile\n6)Log out\n> ");
                input = inputs.next();
            }

        if (input.equals("1"))
        {
            System.out.print("What's happening?\n- ");
            inputs.nextLine();
            String post = inputs.nextLine();
            while (post.length()>200){
                System.out.print("Post content character limit: 200, try again!\n- ");
                post = inputs.nextLine();
            }
            user.setPosts(post);
        }
        else if (input.equals("2"))
        {
            if (user.postNUm==0){
                System.out.println("You haven't posted yet!");
            }else {
                System.out.println("Posts:");
                for (int i = user.postNUm-1; i >= 0; i--) {
                    System.out.println(i+1 +") "+user.getPosts(i));
                    if (user.getLikes(i)==0){
                        System.out.println("Not liked yet!");
                    }
                    else {
                        System.out.println("likes: "+ user.getLikes(i));
                    }
                    for (int k = 0; k < 10; k++) {
                        if (user.getComment(i,k)!= null){
                            System.out.println("---) "+ user.getComment(i,k));
                        }
                    }
                    System.out.print("Edit? ");
                    String decide = inputs.next().toUpperCase();
                    if (decide.equals("YES")){
                        inputs.nextLine();
                        user.editPost(i, inputs.nextLine());
                    }
                    System.out.print("Delete? ");
                    decide = inputs.next().toUpperCase();
                    if (decide.equals("YES")){
                        user.deletePost(i);
                    }
                }
            }
        }
        else if (input.equals("3"))
        {
            if (checkFler(user)){
                System.out.println("Followers:");
                for (int i = 0; i < user.flowerNum; i++) {
                    if (user.getFlowers(i) != null) {
                        System.out.println("-) " + user.getFlowers(i));
                    }
                }
            }else {
                System.out.println("You haven't followed anyone yet!");
            }
            if (checkFling(user)){
                System.out.println("Following:");
                for (int i = 0; i < user.flowingNum; i++) {
                    if (user.getFlowing(i) != null) {
                        System.out.println("-) " + user.getFlowing(i));
                    }
                }
            }else {
                System.out.println("No one follows you yet!");
            }
        }
        else if (input.equals("4"))
        {
            search(users, user);
        }
        else if (input.equals("5")) {
            changeProfile(user);
        }
        } while (!Objects.equals(input, "6"));
    }

    public void changeProfile(User user){
        int tryy = 0;
        Scanner inputt = new Scanner(System.in);
        System.out.print("Which information do you want to edit? \n1)Username\n2)Password\n3)Bio\n4)Return to menu\n> ");
        String input = inputt.next();
        while (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3")&& !Objects.equals(input, "4"))
        {
            System.out.print( "Your choice is not among the options!\n1)Username\n2)Password\n3)Bio\n4)Return to menu\n> ");
            input = inputt.next();
            ++tryy;
            if(tryy==3){
                System.out.print("Do you want to continue?\n> ");
                String answer = inputt.next().toUpperCase();
                if (answer.equals("NO")){
                    return;
                }
            }
        }
        if (input.equals("1")){
            System.out.println("Your username is "+ user.getUsername());
            System.out.print("Enter new username:\n- ");
            String username = inputt.next();
            while (!user.checkPassA(username) || !user.checkPassD(username)){
                System.out.print("Username must be the combination of digits and alphabets.\n- ");
                username = inputt.next();
            }
            user.setUsername(username);
        } else if (input.equals("2")) {
            System.out.println("Your password is "+ user.getPassword());
            System.out.print("Enter new password: \n- ");
            String password = inputt.next();
            int trry=0;
            while (!user.checkPass(password)){
                if (trry>=3){
                    System.out.println("The given password is not valid, Try again!");
                    System.out.print("Password must be between 8 to 20 characters and must be the combination of digits and alphabets.\n- ");
                }else {
                    System.out.print("The given password is not valid, Try again!\n- ");
                }
                password = inputt.next();
                ++trry;
            }
            user.setPassword(password);
        } else if (input.equals("3")) {
            System.out.println("Your bio is "+ user.getBio());
            System.out.print("Enter new bio: \n- ");
            inputt.nextLine();
            user.setBio(inputt.nextLine());
        } else if (input.equals("4")) {
            return;
        }
    }
    public void search(User[] users, User user){
        Scanner inputt = new Scanner(System.in);
        System.out.print("Try searching for people!\n--> ");
        String username = inputt.next();
        for (int i = 0; i < Main.len; i++) {
            if (Objects.equals(username, users[i].getUsername())) {
                System.out.println("Username: "+ users[i].getUsername());
                System.out.println("Bio: "+ users[i].getBio());
                if (users[i].postNUm==0){
                    System.out.println(users[i].getUsername()+ " Hasn't posted yet!");
                }else{
                    System.out.println("Posts:");
                    for (int j = users[i].postNUm-1; j >= 0; j--) {
                        System.out.println(j+1 +") "+ users[i].getPosts(j));
                        if (users[i].getLikes(j)==0){
                            System.out.println("Not liked yet!");
                        }
                        else {
                            System.out.println("likes: "+ users[i].getLikes(j));
                        }
                        for (int k = 0; k < 10; k++) {
                            if (users[i].getComment(j,k)!= null){
                                System.out.println("---) "+ users[i].getComment(j,k));
                            }
                        }
                        System.out.print("Commet? ");
                        String decide = inputt.next().toUpperCase();
                        if (decide.equals("YES")){
                            addComment(users[i], user, users,j);
                        }
                        System.out.print("Like? ");
                        decide = inputt.next().toUpperCase();
                        if (decide.equals("YES")){
                            likePost(users[i] ,j);
                        }
                    }
                }
                if (!users[i].getUsername().equals(user.getUsername())){
                    if (checkFollow(user, users[i])) {
                        System.out.print("Do you want to unfollow " + users[i].getUsername() + "?\n> ");
                        String decide = inputt.next().toUpperCase();
                        if (decide.equals("YES")) {
                            user.setIngUn(findUserIng(user, users[i]));
                            users[i].setErUn(findUserEr(user, users[i]));
                        }
                    } else {
                        System.out.print("Do you want to follow " + users[i].getUsername() + "?\n> ");
                        String decide = inputt.next().toUpperCase();
                        if (decide.equals("YES")) {
                            user.setFlowing(users[i].getUsername());
                            users[i].setFlowers(user.getUsername());
                        }
                    }
                }
                return;
            }
        }
        System.out.println("No results for "+ username);
    }
    public int findUserEr(User user1, User user2){
        for (int i = 0; i < user2.flowerNum; i++) {
            if (user2.getFlowers(i).equals(user1.getUsername())){
                return i;
            }
        }
        return -1;
    }
    public int findUserIng(User user1, User user2){
        for (int i = 0; i < user1.flowingNum; i++) {
            if (user1.getFlowing(i).equals(user2.getUsername())){
                return i;
            }
        }
        return -1;
    }
    public boolean checkFollow(User user1, User user2){
        for (int i = 0; i < user1.flowingNum; i++) {
            if (user1.getFlowing(i).equals(user2.getUsername())){
                return true;
            }
        }
        return false;
    }
    public void addComment(User user1, User user2, User[] users,int j) {
        Scanner inputt = new Scanner(System.in);
        int postNumber = j;
        System.out.print("Enter your comment:\n- ");
//        inputt.nextLine();
        String comment = inputt.nextLine();
        user1.addComment(postNumber, comment);
        System.out.println("_successful!");
    }
    public void likePost(User user, int post) {
        user.likePost(post);
        System.out.println("_successful!");
    }

    public boolean checkFling(User user){
        for (int i = 0; i < user.flowingNum; i++) {
            if (user.getFlowing(i) != null){
                return true;
            }
        }
        return false;
    }

    public boolean checkFler(User user){
        for (int i = 0; i < user.flowerNum; i++) {
            if (user.getFlowers(i) != null){
                return true;
            }
        }
        return false;
    }
}