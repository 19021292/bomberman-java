package gameobjects;

import util.Vector2D;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Bomber extends Player implements Observable {

    private BufferedImage[][] sprites;

    private int moveSpeed;

    public Bomber(float xPos, float yPos, BufferedImage spriteMap) {
        super(xPos, yPos - 16);

        // Each sprite is 32x48. Sprite map is 6x4 sprites
        this.sprites = new BufferedImage[spriteMap.getHeight() / 48][spriteMap.getWidth() / 32];
        for (int row = 0; row < spriteMap.getHeight() / 48; row++) {
            for (int column = 0; column < spriteMap.getWidth() / 32; column++) {
                this.sprites[row][column] = spriteMap.getSubimage(column * 32, row * 48, 32, 48);
            }
        }

        this.sprite = this.sprites[1][0];
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();
        this.originOffset = new Vector2D(this.width / 2, this.height / 2);
        this.collider = new Rectangle2D.Double(xPos, yPos, this.width, this.height - 16);

        this.moveSpeed = 1;
    }

    private void moveUp() {
        this.position.addY(-this.moveSpeed);
    }
    private void moveDown() {
        this.position.addY(this.moveSpeed);
    }
    private void moveLeft() {
        this.position.addX(-this.moveSpeed);
    }
    private void moveRight() {
        this.position.addX(this.moveSpeed);
    }

    @Override
    public void collides(GameObject collidingObj) {
        collidingObj.handleCollision(this);
    }

    @Override
    public void handleCollision(Bomber collidingObj) {

    }

    @Override
    public void handleCollision(Wall collidingObj) {
        this.solidCollision(collidingObj);
    }

    @Override
    public void handleCollision(Explosion collidingObj) {

    }

    @Override
    public void update() {
        this.collider.setRect(this.position.getX() + 2, this.position.getY() + 20, this.width - 4, this.height - 20);

        // Movement
        if (this.UpPressed) {
            this.moveUp();
        }
        if (this.DownPressed) {
            this.moveDown();
        }
        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
    }

}
