package com.Minigolf.game.Global;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
<<<<<<< HEAD
import com.Minigolf.game.Screens.Endless;
import com.Minigolf.game.Global.Gameplay;
=======
>>>>>>> 244e0f4e51290da1d55c213778e68b9e998804dc

public class Contact implements ContactListener{

    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        /*Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if(fa == null || fb == null) return;
        if(fa.getUserData() = null || fb.getUserData() == null) return;
        System.out.println("Collision");*/
    }

    @Override
    public void endContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        
    }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {
        
    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {
        
    }
}