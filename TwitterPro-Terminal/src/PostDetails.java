public class PostDetails {
    int comNum =0;
    int likeNum =0;
    private String[][] comments = new String[200][100];

    public String getComments(int post ,int num) {
        return comments[post][num];
    }

    public void setComments(int post,String comment) {
        this.comments[post][comNum] = comment;
        ++comNum;
    }
    public void details(){

    }
}
