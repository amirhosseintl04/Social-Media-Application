import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class UserMenu {

    public void menu(User user, User[] users){
        Scanner inputs = new Scanner(System.in);
        String input;
        do{
            input = JOptionPane.showInputDialog("Please select a choice!\n1) New post\n2) Posts\n3) Flowers and flowing\n4) Search\n5) Edit profile\n6) Log out");
            while (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3")&& !Objects.equals(input, "4")&& !Objects.equals(input, "5") && !Objects.equals(input, "6")) {
                input = JOptionPane.showInputDialog("Your choice is not among the options!\n1) New post\n2) Posts\n3) Flowers and flowing\n4) Search\n5) Edit profile\n6) Log out");
            }

        if (input.equals("1"))
        {
            String post = JOptionPane.showInputDialog("What's happening?");
            while (post.length()>200){
                post = JOptionPane.showInputDialog("Post content character limit: 200.\nTry again!");
            }
            while (post.equals("") || post.equals(null)){
                post = JOptionPane.showInputDialog("Try again!");
            }
            user.setPosts(post);
        }
        else if (input.equals("2"))
        {
            if (user.postNUm==0){
                JOptionPane.showMessageDialog(null,"You haven't posted yet!");
            }else {
                String decide ="";
                String chois;
                for (int i = user.postNUm-1; i >= 0; i--) {
                    do {
                        decide ="";
                        decide += (i + 1 + ") " + user.getPosts(i));
                        if (user.getLikes(i) == 0) {
                            decide += "\nNot liked yet!\n";
                        } else {
                            decide += ("\nlikes: " + user.getLikes(i) + "\n");
                        }
                        for (int k = 0; k < 10; k++) {
                            if (user.getComment(i, k) != null) {
                                decide += ("---) " + user.getComment(i, k)+"\n");
                            }
                        }
                        chois = JOptionPane.showInputDialog(decide + "\n1) Edit\n2) Delete\n3) Return");
                        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                            chois = JOptionPane.showInputDialog(decide + "\n1) Edit\n2) Delete\n3) Return");
                        }
                        if (chois.equals("1")) {
                            String edit = JOptionPane.showInputDialog("Edit post");
                            user.editPost(i, edit);
                        } else if (chois.equals("2")) {
                            user.deletePost(i);
                            JOptionPane.showMessageDialog(null,"successful!" );
                            chois = "3";
                        }
                    }while (!chois.equals("3"));
                }
            }
        }
        else if (input.equals("3"))
        {
            String followerNames = "";
            if (checkFler(user)){
                for (int i = 0; i < user.flowerNum; i++) {
                    if (user.getFlowers(i) != null) {
                        followerNames += "-) " + user.getFlowers(i) + "\n";
                    }
                }
            }
            String follwingNames = "";
            if (checkFling(user)){
                for (int i = 0; i < user.flowingNum; i++) {
                    if (user.getFlowing(i) != null) {
                        follwingNames += "-) " + user.getFlowing(i) + "\n";
                    }
                }
            }

            if (checkFling(user) && checkFler(user)){
                JOptionPane.showMessageDialog(null, "Followers:\n" + followerNames+"\n"+ "Following:\n" + follwingNames);
            } else if (checkFler(user) && !checkFling(user)) {
                JOptionPane.showMessageDialog(null, "Followers:\n" + followerNames+"\n"+"You haven't followed anyone yet!");
            } else if (!checkFler(user) && checkFling(user)) {
                JOptionPane.showMessageDialog(null, "No one follows you yet!" + "\n"+ "Following:\n" + follwingNames);
            }else {
                JOptionPane.showMessageDialog(null, "No one follows you yet!" + "\n"+ "You haven't followed anyone yet!");

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
        String input = JOptionPane.showInputDialog("Which information do you want to edit? \n1)Username\n2)Password\n3)Bio\n4)Return to menu");
        while (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3")&& !Objects.equals(input, "4"))
        {
            input = JOptionPane.showInputDialog("Your choice is not among the options!\n1)Username\n2)Password\n3)Bio\n4)Return to menu");
            ++tryy;
            if(tryy==3){
                String answer = JOptionPane.showInputDialog("Do you want to continue?").toUpperCase();
                if (answer.equals("NO")){
                    return;
                }
            }
        }
        if (input.equals("1")){
            String username = JOptionPane.showInputDialog("Your username is "+ user.getUsername()+"\nEnter new username:");
            while (!user.checkPassA(username) || !user.checkPassD(username)){
                username = JOptionPane.showInputDialog("Username must be the combination of digits and alphabets.");
            }
            user.setUsername(username);
        } else if (input.equals("2")) {

            String password = JOptionPane.showInputDialog("Your password is "+ user.getPassword()+"\nEnter new password:");
            int trry=0;
            while (!user.checkPass(password)){
                if (trry>=3){
                    password = JOptionPane.showInputDialog("The given password is not valid, Try again!\nPassword must be between 8 to 20 characters and must be the combination of digits and alphabets.");
                }else {
                    password = JOptionPane.showInputDialog("The given password is not valid, Try again!");
                }
                ++trry;
            }
            user.setPassword(password);
        } else if (input.equals("3")) {
            String bio = JOptionPane.showInputDialog(null,"Your bio is "+ user.getBio()+ "\nEnter new bio:");
            user.setBio(bio);
        } else if (input.equals("4")) {
            return;
        }
    }

    public void search(User[] users, User user){
        String username = JOptionPane.showInputDialog("Try searching for people!");
        for (int i = 0; i < Main.len; i++) {
            if (Objects.equals(username, users[i].getUsername())) {
                String input;
                do {
                    String text = "";
                    text += ("Username: " + users[i].getUsername() + "\n" + "Bio: " + users[i].getBio() + "\n1) Posts");
                    if (!users[i].getUsername().equals(user.getUsername())) {
                        boolean follow = checkFollow(user, users[i]);
                        if (follow) {
                            text += "\n2) Unfollow";
                        } else {
                            text += "\n2) Follow";
                        }
                    }
                    text += "\n3) Retun to menu";
                    input = JOptionPane.showInputDialog(text);
                    while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                        input = JOptionPane.showInputDialog(text);
                    }
                    if (input.equals("1")) {
                        if (users[i].postNUm==0){
                            JOptionPane.showMessageDialog(null,users[i].getUsername()+ " hasn't posted yet!");
                        }else{
                            String decide ="";
                            String chois;
                            for (int j = users[i].postNUm-1; j >= 0; j--) {
                                boolean like = false;
                                do {
                                    decide ="";
                                    decide += (j + 1 + ") " + users[i].getPosts(j));
                                    if (users[i].getLikes(j) == 0) {
                                        decide += "\nNot liked yet!\n";
                                    } else {
                                        decide += ("\nlikes: " + users[i].getLikes(j) + "\n");
                                    }
                                    for (int k = 0; k < 10; k++) {
                                        if (users[i].getComment(j, k) != null) {
                                            decide += ("---) " + users[i].getComment(j, k)+"\n");
                                        }
                                    }
                                    chois = JOptionPane.showInputDialog(decide + "\n1) Like\n2) Comment\n3) Return");
                                    while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                                        chois = JOptionPane.showInputDialog(decide + "\n1) Like\n2) Comment\n3) Return");
                                    }
                                    if (chois.equals("1")) {
                                        if (!like){
                                            likePost(users[i], j);
                                            like = true;
                                        }else {
                                            JOptionPane.showMessageDialog(null,"You already liked it!");
                                        }
                                    } else if (chois.equals("2")) {
                                        addComment(users[i], user, users,j);
                                    }
                                }while (!chois.equals("3"));
                            }

                        }


                    } else if (input.equals("2")) {
                        if (checkFollow(user, users[i])) {
                            user.setIngUn(findUserIng(user, users[i]));
                            users[i].setErUn(findUserEr(user, users[i]));
                            JOptionPane.showMessageDialog(null,"You unfollowed "+ users[i].getUsername()+"!");
                        } else {
                            user.setFlowing(users[i].getUsername());
                            users[i].setFlowers(user.getUsername());
                            JOptionPane.showMessageDialog(null,"You followed "+ users[i].getUsername()+"!");

                        }
                    }
                }while (!input.equals("3"));
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"No results for "+ username);
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
            if (user1.getFlowing(i) != null) {
                if (user1.getFlowing(i).equals(user2.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }
    public void addComment(User user1, User user2, User[] users,int j) {
        Scanner inputt = new Scanner(System.in);
        int postNumber = j;
        String comment = JOptionPane.showInputDialog("Enter your comment:");
        user1.addComment(postNumber, comment);
        JOptionPane.showMessageDialog(null,"successful!" );
    }
    public void likePost(User user, int post) {
        user.likePost(post);
        JOptionPane.showMessageDialog(null,"successful!" );
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