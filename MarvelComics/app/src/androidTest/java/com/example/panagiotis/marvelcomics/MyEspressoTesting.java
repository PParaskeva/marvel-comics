package com.example.panagiotis.marvelcomics;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class MyEspressoTesting extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivity mainActivity;
    public MyEspressoTesting() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception{
        super.setUp();
        mainActivity=getActivity();
    }

    public void testWhiteBox1(){
        onView(withId(R.id.recycleView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
    }

    public void testWhiteBox2(){
        onView(withId(R.id.fab)).perform(click());
    }

    public void testWhiteBox3(){
        //min_editText
        //Max_editText
        //search_button

        onView(withId(R.id.min_editText))
                .perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.Max_editText))
                .perform(typeText("6"), closeSoftKeyboard());

        onView(withId(R.id.search_button)).perform(click());

    }
    private String getString(int resId){
        return getInstrumentation().getTargetContext().getString(resId);
    }
//    public void testWhiteBox2(){
//        onView(withId(R.id.recycleView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
//    }

}
