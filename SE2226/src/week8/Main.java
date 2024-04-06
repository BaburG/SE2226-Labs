package week8;

public class Main {
    public static void main(String[] args) {
        String URL = "https://www.imdb.com/search/title/?groups=top_250&sort=user_rating,asc";

        Bot bot = new Bot(URL);
        bot.run();
    }
}
