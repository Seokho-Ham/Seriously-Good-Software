package chap09;

public class PostSummary {

    private int likeCount;

    public PostSummary(int likeCount) {
        this.likeCount = likeCount;
    }

    public PostSummary() {}

    public void update(int likes) {
        likeCount += likes;
    }

    public PostSummary merge(PostSummary summary) {
        return new PostSummary(likeCount + summary.likeCount);
    }

    public int getCount() {
        return likeCount;
    }

    public static final Attribute<Integer, PostSummary> ops = Attribute.of(PostSummary::new,
            PostSummary::update,
            PostSummary::merge,
            PostSummary::getCount);
}
