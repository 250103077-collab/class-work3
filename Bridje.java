interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int level);
}
class Radio implements Device{
    public void turnOn(){
        System.out.println("radio starts playing static/music.");
    }
    public void turnOff(){
        System.out.println("radio turns off.");
    }
    public void setVolume(int level){
        System.out.println("Radio volume knob turned to: " + level);
    }
}
class SmartLight implements Device {
    public void turnOn(){
        System.out.println("smart light glows bright white.");
    }

    public void turnOff(){
        System.out.println("smart light shuts off.");
    }

    public void setVolume(int level){
        System.out.println("smart light brightness set to: " + level + "%");
    }
}
class BasicRemote{
    protected Device device;
    public BasicRemote(Device device){
        this.device = device;
    }
    public void powerOn(){
        device.turnOn();
    }
    public void powerOff(){
        device.turnOff();
    }
}

class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("[remote] Mute button pressed!");
        device.setVolume(0);
    }
}
class PartyRemote extends BasicRemote {
    public PartyRemote(Device device) {
        super(device);
    }

    public void partyMode() {
        System.out.println("[party remoote] pumping up the party!");
        device.turnOn();
        device.setVolume(100);
    }
}
interface Weapon {
    void use();
}

class Sword implements Weapon {
    public void use(){
        System.out.println("slash! Dealing 20 physical damage.");
    }
}

class MagicWand implements Weapon {
    public void use(){
        System.out.println("Zap! Dealing 35 magic damage.");
    }
}
abstract class Hero{
    protected Weapon weapon;

    public Hero(Weapon weapon){
        this.weapon = weapon;
    }

    public abstract void attack();
}
class Knight extends Hero{
    public Knight(Weapon weapon){
        super(weapon);
    }

    public void attack(){
        weapon.use();
    }
}

class Wizard extends Hero {
    public Wizard(Weapon weapon){
        super(weapon);
    }

    public void attack(){
        weapon.use();
    }
}

public class BridgeLab {
    public static void main(String[] args) {
        System.out.println("Task 4");
        Device light = new SmartLight();
        AdvancedRemote remote = new AdvancedRemote(light);
        remote.powerOn();
        remote.mute();

        System.out.println("\nTask 5");
        PartyRemote party = new PartyRemote(new Radio());
        party.partyMode();

        System.out.println("\nTask 6");
        Hero knight1 = new Knight(new Sword());
        knight1.attack();
        Hero knight2 = new Knight(new MagicWand());
        knight2.attack();
    }
}
