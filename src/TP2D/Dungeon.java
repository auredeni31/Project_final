package TP2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Dungeon {
    private final int height;
    private final int width;
    private final TileManager tileManager;
    private ArrayList <Things> renderList = new ArrayList<>();

    private char[][] map;
    public Dungeon(int height, int width) {
        this.height = height;
        this.width = width;
        this.tileManager = new TileManager(32,32);
        this.map = new char[width][height];
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                if ((x==0)||(x==width-1)||(y==0)||(y==height-1)){
                    this.map[x][y]='W';
                }
                else {
                    this.map[x][y]=' ';
                }
            }
        }
        respawnListOfThings();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.map = new char[width][height];
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                if ((x==0)||(x==width-1)||(y==0)||(y==height-1)){
                    this.map[x][y]='W';
                }
                else {
                    this.map[x][y]=' ';
                }
            }
        }
        respawnListOfThings();
    }

    public Dungeon(String fileName, TileManager tileManager) {
        this.tileManager = tileManager;
        int height=0;
        int width=0;
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.readLine()!=null){
                height++;
            }
            br.close();
            br = new BufferedReader(new FileReader(fileName));
            String s = br.readLine();
            width = s.length();
            this.map = new char[width][height];
            for (int y=0;y<height;y++){
                for (int x=0;x<width;x++){
                    this.map[x][y]=s.toCharArray()[x];
                }
                s=br.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.height = height;
            this.width = width;
        }


        respawnListOfThings();
    }


    private void respawnListOfThings(){
        renderList.clear();
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                switch (this.map[x][y]){
                    case ' ' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(1,9)));
                                break;
                    case 'W' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(8,2)));
                        break;
                    case 'w' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(8,6)));
                        break;
                    case 'L' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(2,1)));
                        break;
                    case 'l' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(1,13)));
                        break;
                    case 'R' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(2,0)));
                        break;
                    case 'r' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(8,7)));
                        break;
                    case 'A' :  renderList.add(new Trap(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(7,7)));
                        break;
                    case 'B' :  renderList.add(new Trap(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(7,6)));
                        break;
                    case 'Q' :  renderList.add(new Trap(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(1,13)));
                        break;
                    case 'b' :  renderList.add(new Trap(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(7,4)));
                        break;
                    case 'c' :  renderList.add(new Trap(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(2,9)));
                        break;
                    case 's' :  renderList.add(new Trap(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(8,11)));
                        break;
                }
            }
        }
    }

    public void displayDungeonInConsole(HitBox hero){
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                if (x==(hero.getX()/ tileManager.getWidth()) && y==(hero.getY()/ tileManager.getHeigth())) {
                    System.out.print("H");
                }
                else {
                    System.out.print((map[x][y]));
                }
            }
            System.out.println();
        }

    }

    public ArrayList<Things> getRenderList() {
        return renderList;
    }
}
