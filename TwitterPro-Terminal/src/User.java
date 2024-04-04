import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class User {
    int postNUm =0;
    int flowingNum =0;
    int flowerNum =0;
    static boolean confirm = true;
    static boolean capcha = true;
    private String[][] comments = new String[200][10];
    private int[] likes = new int[200];
    public void likePost(int post) {
        likes[post]++;
    }
    public int getLikes(int post) {
        return likes[post];
    }

    public String getFlowers(int nuum) {
        return flowers[nuum];
    }

    public void setFlowers(String flowers) {
        this.flowers[flowerNum] = flowers;
        ++flowerNum;
    }
    public void setIngUn(int num){
        this.flowing[num] = null;
    }
    public void setErUn(int num){
        this.flowers[num] = null;
    }

    public String getFlowing(int nuuum) {
        return flowing[nuuum];
    }

    public void setFlowing(String flowing) {
        this.flowing[flowingNum] = flowing;
        ++flowingNum;
    }
    private String[] flowers = new String[300];
    private String[] flowing = new String[300];
    static boolean log = true;
    private String username;
    private String password;
    private String bio;
    private String[] posts = new String[200];
    public String getPosts(int num) {
        return posts[num];
    }
    public void setPosts(String posts) {
        this.posts[postNUm] = posts;
        ++postNUm;
    }

    public void editPost(int num, String newPost){
        this.posts[num] = newPost;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public boolean checkPassA(String password)
    {
        for (int i = 0; i < password.length(); i++) {
            char check = password.charAt(i);
            if (Character.isAlphabetic(check)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPassD(String password)
    {
        for (int i = 0; i < password.length(); i++) {
            char check = password.charAt(i);
            if (Character.isDigit(check)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPass(String password){
        if (password.length() < 8 || password.length() > 20 || !checkPassA(password) || !checkPassD(password))
        {
            return false;
        }
        return true;
    }
    public boolean available(String username,User[] users){
        for (int i = 0; i < Main.len; i++) {
            if(username.equals(users[i].getUsername())){
                return false;
            }
        }
        return true;
    }
    static public void signUp(User user,User[] users){
        Scanner inputt = new Scanner(System.in);
        System.out.print("Enter username:\n- ");
        String username = inputt.next();
        while (!user.checkPassA(username) || !user.checkPassD(username)){
            System.out.print("Username must be the combination of digits and alphabets.\n- ");
            username = inputt.next();
        }
        while (!user.available(username,users)){
            System.out.print("The username given is not available,try again:\n- ");
            username = inputt.next();
        }
        System.out.print("Enter password:\n- ");
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
        confirm(password);
        if (confirm){
            capcha();
        }
        if (confirm && capcha){
            user.setUsername(username);
            user.setPassword(password);
            System.out.print("Enter bio:\n- ");
            inputt.nextLine();
            user.setBio(inputt.nextLine());
            ++Main.len;
        }
    }

    static public void login(User[] users){
        UserMenu userMenu = new UserMenu();
        Scanner inputt = new Scanner(System.in);
        System.out.print("Enter username:\n- ");
        String username = inputt.next();
        System.out.print("Enter password:\n- ");
        String password = inputt.next();
        int tryy = 0;
        for (int i = 0; i < Main.len; i++)
        {
            if (Objects.equals(username, users[i].username))
            {
                if (Objects.equals(password, users[i].password))
                {
                    System.out.println("Welcome "+ username);
                    userMenu.menu(users[i], users);
                    return;
                }
                else if (!Objects.equals(password, users[i].password))
                {
                    do
                    {
                        tryy++;
                        System.out.print("The password was incorrect, Enter password:\n- ");
                        password = inputt.next();
                        if(tryy==5){
                            System.out.println("Do you want to continue? ");
                            String answer = inputt.next().toUpperCase();
                            if (answer.equals("NO")){
                                log = false;
                                return;
                            }
                        }

                    } while (!Objects.equals(password, users[i].password));
                    System.out.println("Welcome "+ username);
                    userMenu.menu(users[i], users);
                    return;
                }
            }
        }
        System.out.println("Username not found!");
    }

    public String getComment(int post, int comment) {
        return comments[post][comment];
    }

    public void addComment(int post, String comment) {
        for (int i = 0; i < comments[post].length; i++) {
            if (comments[post][i] == null) {
                comments[post][i] = comment;
                break;
            }
        }
    }

    public void deletePost(int numPOst) {
        if (numPOst >= 0 && numPOst < postNUm) {
            for (int i = numPOst; i < postNUm - 1; i++) {
                posts[i] = posts[i + 1];
            }
            posts[postNUm - 1] = null;
            postNUm--;
            for (int i = numPOst; i < comments.length - 1; i++) {
                comments[i] = comments[i + 1];
            }
            comments[comments.length - 1] = null;

            for (int i = numPOst; i < likes.length - 1; i++) {
                likes[i] = likes[i + 1];
            }
            likes[likes.length - 1] = 0;
        }
    }

    static public void confirm(String password){
        Scanner inputt = new Scanner(System.in);
        System.out.print("Confirm your password:\n- ");
        String conf = inputt.next();
        int tryy =0;
        while (!conf.equals(password)){
            System.out.print("The given password is not matching, try again:\n- ");
            conf = inputt.next();
            confirm = false;
            ++tryy;
            if (tryy == 3){
                System.out.println("You have tried too much!\nYou will be returned to the main menu!");
                return;
            }
        }
        confirm = true;
    }
    static public void capcha(){
        Scanner inputt = new Scanner(System.in);
        Random rand = new Random();
        int number1 = rand.nextInt(20);
        int number2 = rand.nextInt(20);
        int sum = number2+number1;
        System.out.print("Whats the sum of these two numbers? "+ number1 +" and "+ number2+"\n- ");
        int result = inputt.nextInt();
        if (result != sum){
            System.out.println("YOU ARE A ROBOT");
            capcha = false;
            return;
        }
    }
}
