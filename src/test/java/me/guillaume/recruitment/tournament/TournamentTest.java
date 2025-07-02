package me.guillaume.recruitment.tournament;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This is a duel simulation system.
 *
 * Combat exchanges are sequential: when A engages B, A delivers the first blow, then B responds.
 */
public class TournamentTest {


    /**
     * A Swordsman has 100 hit points (health) and uses a one-handed sword that deals 5 damage per blow (points per strike).
     * A Viking has 120 hit points (health) and uses a one-handed axe that deals 6 damage per blow (points per strike).
     */
    @Test
    public void SwordsmanVsViking() {

        Swordsman swordsman = new Swordsman();

        Viking viking = new Viking();

        swordsman.engage(viking);

        assertThat(swordsman.hitPoints()).isEqualTo(0);
        assertThat(viking.hitPoints()).isEqualTo(35);

    }

    /**
     * A buckler cancels all damage from a blow every other time (one time out of two).
     * A buckler is destroyed after blocking 3 blows from an axe.
     */
    @Test
    public void SwordsmanWithBucklerVsVikingWithBuckler() {

        Swordsman swordsman = new Swordsman()
                .equip("buckler");

        Viking viking = new Viking()
                .equip("buckler");

        swordsman.engage(viking);

        assertThat(swordsman.hitPoints()).isEqualTo(0);
        assertThat(viking.hitPoints()).isEqualTo(70);

    }

    /**
     * A Highlander has 150 hit points (health) and fights with a Great Sword.
     * A Great Sword is a two-handed weapon that delivers 12 damage (points per strike), but can only attack 2 out of every 3 rounds.
     * Armor reduces all received damage by 3 points and reduces delivered damage by 1 point.
     */
    @Test
    public void ArmoredSwordsmanVsViking() {

        Highlander highlander = new Highlander();

        Swordsman swordsman = new Swordsman()
                .equip("buckler")
                .equip("armor");

        swordsman.engage(highlander);

        assertThat(swordsman.hitPoints()).isEqualTo(0);
        assertThat(highlander.hitPoints()).isEqualTo(10);

    }

    /**
     * A vicious Swordsman is a Swordsman who applies poison to his weapon.
     * Poison adds 20 additional damage (points per strike) to the first two blows only.
     * A veteran Highlander goes Berserk once his hit points (health) drop below 30% of his initial total.
     * Once Berserk, he doubles his damage output.
     */
    @Test // Bonus points :D
    public void ViciousSwordsmanVsVeteranHighlander() {

        Swordsman swordsman = new Swordsman("Vicious")
                .equip("axe")
                .equip("buckler")
                .equip("armor");

        Highlander highlander = new Highlander("Veteran");

        swordsman.engage(highlander);

        assertThat(swordsman.hitPoints()).isEqualTo(1);
        assertThat(highlander.hitPoints()).isEqualTo(0);

    }

}