package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;
import java.util.Random;

import javax.xml.soap.Text;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture birds[];
	int flapstate = 0;
	float birdY = 0;
	float velocity = 0;

	int gamestate = 0;

	float gravity = 2;
	Texture toptube;
	Texture bottomtube;
	float gap = 400;

	float maxtubeoffset;
	Random r;
	float tubevelocity = 4;
	int numberoftubes = 7;
	float[] tubeX = new float[numberoftubes];
	float[] tubeoffset = new float[numberoftubes];

	float distancebetweentubes;


	NativePlatform nativePlatform;
	public FlappyBird(NativePlatform a)
	{
		this.nativePlatform = a;
	}



	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;
		toptube = new Texture("toptube.png");
		bottomtube = new Texture("bottomtube.png");
		maxtubeoffset = Gdx.graphics.getHeight()/2 - gap / 2 - 100;
		r = new Random();
		distancebetweentubes = Gdx.graphics.getWidth()/2;


		for (int i = 0;i < numberoftubes;i++)
		{
			tubeoffset[i] = (r.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
			tubeX[i] = Gdx.graphics.getWidth()/2 - toptube.getWidth()/2 + i*distancebetweentubes;

		}

		nativePlatform.CheckConnection();

	}




	@Override
	public void render () {


		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



				if(gamestate != 0) // game is active
				{

					if(Gdx.input.justTouched())
					{
						velocity =  -30;


					}


					for (int i = 0;i < numberoftubes;i++) {

						if(tubeX[i] < 0 - toptube.getWidth())
						{
							tubeX[i] += numberoftubes *distancebetweentubes;
						}
						else
						{
							tubeX[i] = tubeX[i] - tubevelocity;

						}

						batch.draw(toptube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeoffset[i]);
						batch.draw(bottomtube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeoffset[i]);

					}


			if(birdY>0 || velocity < 0)
			{
				velocity += gravity;
				birdY -= velocity;
			}

		}else
		{


			if(Gdx.input.justTouched())
			{
				gamestate = 1;
			}
		}


		if (flapstate == 0) {
			flapstate = 1;
		} else {
			flapstate = 0;
		}








		batch.draw(birds[flapstate], Gdx.graphics.getWidth() / 2 - birds[flapstate].getWidth() / 2, birdY);
		batch.end();
	}
	

}
