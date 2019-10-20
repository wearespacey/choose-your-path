package com.keusmar.cslabs_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ChooseCharacterActivity extends AppCompatActivity {
    private ImageView left_arrow, right_arrow, character_image;
    private Button select_character;
    private ArrayList<Integer> characters = new ArrayList<>();
    private Integer currentCharacter = 0;
    public static final String COLOR = "COLOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_character);

        characters.add(R.drawable.alien_beige);
        characters.add(R.drawable.alien_blue);
        characters.add(R.drawable.alien_green);
        characters.add(R.drawable.alien_pink);
        characters.add(R.drawable.alien_yellow);

        left_arrow = findViewById(R.id.previous_character);
        right_arrow = findViewById(R.id.next_character);
        character_image = findViewById(R.id.character_image);
        select_character = findViewById(R.id.getinfo);

        left_arrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentCharacter--;
                if(currentCharacter < 0)
                    currentCharacter = characters.size() - 1;
                character_image.setImageResource(characters.get(currentCharacter));
            }
        });

        right_arrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentCharacter++;
                if(currentCharacter == characters.size())
                    currentCharacter = 0;
                character_image.setImageResource(characters.get(currentCharacter));
            }
        });

        select_character.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCharacterActivity.this, MainActivity.class);
                Character.CharacterColor color = null;
                switch(characters.get(currentCharacter)){
                    case R.drawable.alien_beige:
                        color = Character.CharacterColor.ALIEN_BEIGE;
                    break;
                    case R.drawable.alien_blue:
                        color = Character.CharacterColor.ALIEN_BLUE;
                    break;
                    case R.drawable.alien_green:
                        color = Character.CharacterColor.ALIEN_GREEN;
                    break;
                    case R.drawable.alien_yellow:
                        color = Character.CharacterColor.ALIEN_YELLOW;
                    break;
                    case R.drawable.alien_pink:
                        color = Character.CharacterColor.ALIEN_PINK;
                    break;
                }
                intent.putExtra(COLOR,color);
                startActivity(intent);
            }
        });
    }

}
