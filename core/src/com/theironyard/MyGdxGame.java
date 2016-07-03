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
	TextureRegion up, down, left, right;

	float x, y = 0;
	boolean faceRight = true;
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final int DRAW_WIDTH = WIDTH * 2;
	static final int DRAW_HEIGHT = HEIGHT * 2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, WIDTH, HEIGHT);
		down = grid[6][0];
		up = grid[6][1];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
	}

	@Override
	public void render () {
		move();

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		TextureRegion img = right;

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			img = down;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			img = up;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			img = right;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			img = left;
		}

		batch.begin();
		if(faceRight){
			batch.draw(img, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		}
		else {
			batch.draw(img, x + DRAW_WIDTH, y, DRAW_WIDTH * -1, DRAW_HEIGHT);
		}
		batch.end();
	}

	public void move(){
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			y += 1;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				y += 1;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			y -= 1;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				y -= 1;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			x += 1;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				x += 1;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			x -= 1;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				x -= 1;
			}
		}

		if(y < 0){
			y = 480;
		}
		if(y > 480){
			y = 0;
		}
		if(x < 0){
			x = 620;
		}
		if(x > 620){
			x = 0;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
