interface Image {
    void show();
}
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("--> [slow Reading " + fileName + " from hard drive...");
    }
    public void show(){
        System.out.println("displaying " + fileName + " on screen!");
    }
}
class ProxyImage implements Image{
    private String fileName;
    private String userRole;
    private RealImage realImage;

    public ProxyImage(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }

    public void show() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println("[denied] You lack permission to view: " + fileName);
            return;
        }
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}
interface Video {
    void play();
}

class RealVideo implements Video {
    private String fileName;

    public RealVideo(String fileName) {
        this.fileName = fileName;
        System.out.println("--> [dowloading it] Connecting to server & buffering 4K video: " + fileName);
    }

    public void play(){
        System.out.println("Playing video: " + fileName);
    }
}

class ProxyVideo implements Video {
    private String fileName;
    private RealVideo realVideo;
    private int viewCount = 0;

    public ProxyVideo(String fileName){
        this.fileName = fileName;
    }

    public void play() {
        viewCount++;
        System.out.println("--> [sudit] Video has been played " + viewCount + " time(s).");
        if (realVideo == null) {
            realVideo = new RealVideo(fileName);
        }
        realVideo.play();
    }
}
interface Internet {
    void connectTo(String site);
}
class RealInternet implements Internet {
    public void connectTo(String site){
        System.out.println("connected to site: " + site);
    }
}
class SchoolProxyInternet implements Internet {
    private RealInternet realInternet;
    public void connectTo(String site) {
        if (site.equals("gaming.com") || site.equals("socialmedia.com")) {
            System.out.println("[firewall blocked it] Cannot access: " + site);
            return;
        }
        if (realInternet == null) {
            realInternet = new RealInternet();
        }
        realInternet.connectTo(site);
    }
}
public class ProxyLab {
    public static void main(String[] args) {
        System.out.println("=== Task 1 ===");
        Image guest = new ProxyImage("salary_sheet.png", "GUEST");
        Image admin = new ProxyImage("salary_sheet.png", "ADMIN");
        guest.show();
        admin.show();

        System.out.println("\n=== Task 2 ===");
        Video video = new ProxyVideo("Cat_Memes.mp4");
        video.play();
        video.play();
        video.play();

        System.out.println("\n=== Task 3 ===");
        Internet internet = new SchoolProxyInternet();
        internet.connectTo("wikipedia.org");
        internet.connectTo("gaming.com");
    }
}
