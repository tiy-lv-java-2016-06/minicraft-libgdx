package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion stand, jump, up, down, right, left;
    float x, y = 0;
    public static final float MAX_JUMP_VELOCITY = 200;
    public static final float GRAVITY = -40;
    private String faceDirection = null;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        up = grid[6][1];
        right = grid[6][3];
        left = new TextureRegion(right);
        left.flip(true, false);

        jump = grid[7][2];
        stand = grid[6][2];
	}

	@Override
	public void render () {
        move();
		Gdx.gl.glClearColor(0, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(stand, x, y);
        if(faceDirection == "up"){
            batch.draw(up, x, y);
        } else if(faceDirection == "down"){
            batch.draw(down, x ,y);
        } else if(faceDirection == "right"){
            batch.draw(right, x, y);
        } else if(faceDirection == "left"){
            batch.draw(left, x, y);
        }

		batch.end();
	}

    public void move() {
        if(Gdx.input.isKeyPressed(Input.Keys.J)) {
            y = MAX_JUMP_VELOCITY + GRAVITY;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y++;
            faceDirection = "up";
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                y++;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y--;
            faceDirection = "down";
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                y--;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x++;
            faceDirection = "right";
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                x++;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x--;
            faceDirection = "left";
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                x--;
            }
        }


        if(x < -10){
            x = 630;
        }

        if(x > 635){
            x = -1;
        }

        if(y > 465){
            y = 465;
        }

        if(y < 0){
            y = 0;
        }

    }
	@Override
	public void dispose () {
		batch.dispose();
	}
}
