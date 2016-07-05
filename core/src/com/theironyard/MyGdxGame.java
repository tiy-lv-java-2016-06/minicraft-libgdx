package com.theironyard;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class MyGdxGame extends ApplicationAdapter {
    private TextureRegion down, up, right, left;
	private SpriteBatch batch;
	private Texture img;
    String look = "";
    private float time;



	private float x, y, xv, yv = 0;
	public static final float MAX_VELOCITY = 100;

	@Override
	public void create () {
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
		up = grid[6][1];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
        move();
        TextureRegion img = down;
        time += Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, .6f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
        batch.draw(img, x, y);

        if (look == "r"){
            batch.draw(right, x, y);
        }else if (look == "l"){
            batch.draw(left, x, y);
        }else if (look == "u"){
            batch.draw(up, x, y);
        }else if (look == "d"){
            batch.draw(down, x, y);
        }
        batch.end();
	}

    public float decelerate(float velocity){
        float deceleration = 0.95f;
        velocity *= deceleration;
        if(Math.abs(velocity) < 1){
            velocity = 0;
        }
        return velocity;
    }

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void move(){

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;
            look = "u";
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                yv *= 3;
            }
		}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            yv = MAX_VELOCITY * -1;
            look = "d";
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                yv = MAX_VELOCITY * -3;
            }
        }
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            look = "r";
			xv = MAX_VELOCITY;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                xv = MAX_VELOCITY * 3;
            }
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            look = "l";
			xv = MAX_VELOCITY * -1;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                xv = MAX_VELOCITY * -3;
            }
		}

		y += yv * Gdx.graphics.getDeltaTime();
		x += xv * Gdx.graphics.getDeltaTime();

		xv = decelerate(xv);
		yv = decelerate(yv);

        //dude can not go below the ground or disappear in the sky
		if(y < 0){
			y = 0;
		}else if (y > Gdx.graphics.getHeight()-1){
            y = 0;
        }

        //wrap dude around screen horizontally.
        if (x > Gdx.graphics.getWidth()-1){
            x = 0;
        }else if (x < 0){
            x = Gdx.graphics.getWidth();
        }
	}
}