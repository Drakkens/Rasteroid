package Model;

public class Player extends GameObject {
    protected String name;
    protected float bulletDamage;
    protected float bulletSpeed;
    protected int ammo;
    protected int killCount;

    /*--------------------
        Constructor
     --------------------*/

    //Basic constructor
    public Player(Body body) {
        super(body);
        this.bulletDamage = 5;
        this.bulletSpeed = 1;
        this.ammo = 10000;
        this.killCount = 0;
    }

    /*--------------------
        Getters/Setters
     --------------------*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(float bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    /*--------------------
            Methods
     --------------------*/

    public void shoot() {
    }
}
