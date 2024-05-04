package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.ArrayList;

public class GameAssetsManager {
    public static GameAssetsManager gameAssetsManager = new GameAssetsManager();

    public String saveDataPath = "saveData.save";
    public String uiSkin = "skin/pixthulhu-ui.json";
    public String invisiblePic = "invisible-png.png";
    public String alert = "OppMissles/alert.png";
    public Animation<Texture> alertFrames = new Animation<Texture>(0.1f, new Texture(alert) , new Texture(invisiblePic));
    //avatars
    public String defaultAvatar = "Avatars/avatar.png";
    public String avatar1 = "Avatars/avatar1.png";
    public String avatar2 = "Avatars/avatar2.png";
    public String avatar3 = "Avatars/avatar3.png";
    public ArrayList<String> avatars = new ArrayList<String>(){{
        add(avatar1);
        add(avatar2);
        add(avatar3);
    }};

    // songs
    public String song1 = "Songs/1.mp3";
    public String song2 = "Songs/2.mp3";
    public String song3 = "Songs/3.mp3";
    public Music song1Music = Gdx.audio.newMusic(Gdx.files.internal("Songs/1.mp3"));
    public Music song2Music = Gdx.audio.newMusic(Gdx.files.internal("Songs/2.mp3"));
    public Music song3Music = Gdx.audio.newMusic(Gdx.files.internal("Songs/3.mp3"));
    //planes
    public String activePlane = "Fighter Jets/tactical_jet.png";
    public String activePlaneFlipped = "Fighter Jets/tactical_jet_flipped.png";
    public final String tacticalJet = "Fighter Jets/tactical_jet.png";
    public final String tacticalJetFlipped = "Fighter Jets/tactical_jet_flipped.png";
    public final String migJet = "Fighter Jets/mig.png";
    public final String migJetFlipped = "Fighter Jets/mig-flipped.png";
    public final String fullBackground = "full_background.png";
    public final String halfBackground = "roadAndLight.png";
    public final String cloud1 = "Clouds/cloud1.png";
    public final String cloud2 = "Clouds/cloud2.png";
    public final String cloud3 = "Clouds/cloud3.png";
    public final String cloud4 = "Clouds/cloud4.png";
    public final String freezeFrame = "freeze.png";
    public final Texture freezeTexture = new Texture(freezeFrame);

    //
    public final String oppMissleTank = "OppMissles/48x48 - SmallMissileStaticFrame2.png";
    public final String oppMissleMig = "OppMissles/missile.png";


    //missles
    public final String smallMissleFrame1 = "48x48 - Small Missile/48x48 - SmallMissileMovingingFrame1.png";
    public final String smallMissleFrame2 = "48x48 - Small Missile/48x48 - SmallMissileMovingingFrame2.png";
    public final String smallMissleFrame3 = "48x48 - Small Missile/48x48 - SmallMissileMovingingFrame3.png";
    public final String smallMissleFrame4 = "48x48 - Small Missile/48x48 - SmallMissileMovingingFrame4.png";

    public final String largeMissleFrame1 = "48x48 - Large Missile/48x48 - LargeMissileDetonate.gif";
    public final String largeMissleFrame2 = "48x48 - Large Missile/64x64 - LargeMissileMovingingFrame2.png";
    public final String largeMissleFrame3 = "48x48 - Large Missile/64x64 - LargeMissileMovingingFrame3.png";
    public final String largeMissleFrame4 = "48x48 - Large Missile/64x64 - LargeMissileMovingingFrame4.png";

    // collectibles
    public final String radioActiveCollectible = "Collectibles/radioactive.png";
    public final Texture radioActiveCollectibleTexture = new Texture(radioActiveCollectible);
    public final String clusterCollectible = "Collectibles/cluster.png";
    public final Texture clusterCollectibleTexture = new Texture(clusterCollectible);
    //Opss
    public final String truck = "Opps/Truck.png";
    public final String tank = "Opps/tank.png";
    public final String pixelWall = "Opps/pixel-wall.png";
    public final String building = "Opps/building.png";
    public final String kale_ghandi = "Opps/kale_ghandi.png";

    public Texture backGroundGame = new Texture(fullBackground);
    public Texture halfBackgroundGame = new Texture(halfBackground);
    public Texture cloud1Texture = new Texture(cloud1);
    public Texture cloud2Texture = new Texture(cloud2);
    public Texture cloud3Texture = new Texture(cloud3);
    public Texture cloud4Texture = new Texture(cloud4);

    public Texture truckTexture = new Texture(truck);
    public Texture tankTexture = new Texture(tank);
    public Texture pixelWallTexture = new Texture(pixelWall);
    public Texture buildingTexture = new Texture(building);
    public Texture kaleGhandiTexture = new Texture(kale_ghandi);

    //explosion textures
    public final String explosion1 = "explode/1.png";
    public final String explosion2 = "explode/2.png";
    public final String explosion3 = "explode/3.png";
    public final String explosion4 = "explode/4.png";
    public final String explosion5 = "explode/5.png";
    public final String explosion6 = "explode/6.png";
    public final String explosion7 = "explode/7.png";
    public final String explosion8 = "explode/8.png";

    public Texture explosion1Texture = new Texture(explosion1);
    public Texture explosion2Texture = new Texture(explosion2);
    public Texture explosion3Texture = new Texture(explosion3);
    public Texture explosion4Texture = new Texture(explosion4);
    public Texture explosion5Texture = new Texture(explosion5);
    public Texture explosion6Texture = new Texture(explosion6);
    public Texture explosion7Texture = new Texture(explosion7);
    public Texture explosion8Texture = new Texture(explosion8);
    public Animation<Texture> explosionFrames = new Animation<Texture>(0.1f, explosion1Texture , explosion2Texture, explosion3Texture, explosion4Texture, explosion5Texture, explosion6Texture, explosion7Texture, explosion8Texture);

    //fire
    public final String fire1 = "Fire/Fire+Sparks1.png";
    public final String fire2 = "Fire/Fire+Sparks2.png";
    public final String fire3 = "Fire/Fire+Sparks3.png";
    public final String fire4 = "Fire/Fire+Sparks4.png";
    public final String fire5 = "Fire/Fire+Sparks5.png";
    public final String fire6 = "Fire/Fire+Sparks6.png";
    public final String fire7 = "Fire/Fire+Sparks7.png";
    public final String fire8 = "Fire/Fire+Sparks8.png";
    public final String fire9 = "Fire/Fire+Sparks9.png";
    public final String fire10 = "Fire/Fire+Sparks10.png";
    public final String fire11 = "Fire/Fire+Sparks11.png";
    public final String fire12 = "Fire/Fire+Sparks12.png";
    public final String fire13 = "Fire/Fire+Sparks13.png";
    public final String fire14 = "Fire/Fire+Sparks14.png";
    public final String fire15 = "Fire/Fire+Sparks15.png";
    public final String fire16 = "Fire/Fire+Sparks16.png";
    public final String fire17 = "Fire/Fire+Sparks17.png";
    public final String fire18 = "Fire/Fire+Sparks18.png";
    public final String fire19 = "Fire/Fire+Sparks19.png";
    public final Texture fire1Texture = new Texture(fire1);
    public final Texture fire2Texture = new Texture(fire2);
    public final Texture fire3Texture = new Texture(fire3);
    public final Texture fire4Texture = new Texture(fire4);
    public final Texture fire5Texture = new Texture(fire5);
    public final Texture fire6Texture = new Texture(fire6);
    public final Texture fire7Texture = new Texture(fire7);
    public final Texture fire8Texture = new Texture(fire8);
    public final Texture fire9Texture = new Texture(fire9);
    public final Texture fire10Texture = new Texture(fire10);
    public final Texture fire11Texture = new Texture(fire11);
    public final Texture fire12Texture = new Texture(fire12);
    public final Texture fire13Texture = new Texture(fire13);
    public final Texture fire14Texture = new Texture(fire14);
    public final Texture fire15Texture = new Texture(fire15);
    public final Texture fire16Texture = new Texture(fire16);
    public final Texture fire17Texture = new Texture(fire17);
    public final Texture fire18Texture = new Texture(fire18);
    public final Texture fire19Texture = new Texture(fire19);
    public Animation<Texture> fireFrames = new Animation<Texture>(0.1f, fire1Texture, fire2Texture, fire3Texture, fire4Texture, fire5Texture, fire6Texture, fire7Texture, fire8Texture, fire9Texture, fire10Texture, fire11Texture, fire12Texture, fire13Texture, fire14Texture, fire15Texture, fire16Texture, fire17Texture, fire18Texture, fire19Texture);

    //radio active explosions
    public final String radioActiveExplosion1 = "RadioActiveExplode/Nuclear_explosion1.png";
    public final String radioActiveExplosion2 = "RadioActiveExplode/Nuclear_explosion2.png";
    public final String radioActiveExplosion3 = "RadioActiveExplode/Nuclear_explosion3.png";
    public final String radioActiveExplosion4 = "RadioActiveExplode/Nuclear_explosion4.png";
    public final String radioActiveExplosion5 = "RadioActiveExplode/Nuclear_explosion5.png";
    public final String radioActiveExplosion6 = "RadioActiveExplode/Nuclear_explosion6.png";
    public final String radioActiveExplosion7 = "RadioActiveExplode/Nuclear_explosion7.png";
    public final String radioActiveExplosion8 = "RadioActiveExplode/Nuclear_explosion8.png";
    public final String radioActiveExplosion9 = "RadioActiveExplode/Nuclear_explosion9.png";
    public final String radioActiveExplosion10 ="RadioActiveExplode/Nuclear_explosion10.png";
    public final Texture radioActiveExplosion1Texture = new Texture(radioActiveExplosion1);
    public final Texture radioActiveExplosion2Texture = new Texture(radioActiveExplosion2);
    public final Texture radioActiveExplosion3Texture = new Texture(radioActiveExplosion3);
    public final Texture radioActiveExplosion4Texture = new Texture(radioActiveExplosion4);
    public final Texture radioActiveExplosion5Texture = new Texture(radioActiveExplosion5);
    public final Texture radioActiveExplosion6Texture = new Texture(radioActiveExplosion6);
    public final Texture radioActiveExplosion7Texture = new Texture(radioActiveExplosion7);
    public final Texture radioActiveExplosion8Texture = new Texture(radioActiveExplosion8);
    public final Texture radioActiveExplosion9Texture = new Texture(radioActiveExplosion9);
    public final Texture radioActiveExplosion10Texture = new Texture(radioActiveExplosion10);
    public final Animation<Texture> radioActiveExplosionFrames = new Animation<Texture>(0.1f, radioActiveExplosion1Texture, radioActiveExplosion2Texture, radioActiveExplosion3Texture, radioActiveExplosion4Texture, radioActiveExplosion5Texture, radioActiveExplosion6Texture, radioActiveExplosion7Texture, radioActiveExplosion8Texture, radioActiveExplosion9Texture, radioActiveExplosion10Texture);
    public String mainMenuBackground = "bg.png";

    private GameAssetsManager(){

    }

}
