package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class ScreenFAQ extends Activity
{
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
        
        setContentView(R.layout.screen_faq);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				
			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener()
		{

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) 
			{
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() 
	{
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Dr. Advait Patel Tells You How To Improve Your Bed-time Routine");
		listDataHeader.add("Shampoo- Are You Using The Right One ?");
		listDataHeader.add("WHEN DID FRUITS BECOME BAD FOR YOU???");
		listDataHeader.add("Reasons Why Eggs Are The Healthiest Food on The Planet");
		listDataHeader.add("Do you know correct way of drinking water?");
		listDataHeader.add("HOW TO PRACTICE EMOTIONAL FIRST AID");
		listDataHeader.add("People need germs to stay healthy.");
		listDataHeader.add("HEALTH BENEFITS OF COCONUT WATER:");
		listDataHeader.add("Getting less sleep (less than 7 hours) has short term effects as well as long term issues");
		listDataHeader.add("HOW TO PRACTICE EMOTIONAL FIRST AID");
		
		

		// Adding child data
		List<String> advait = new ArrayList<String>();
		advait.add("Here are 7 things to adopt for your bed-time :- ");
		advait.add("(1) Plan For Next Day :-");
		advait.add("(2) Shut Out the Buzz :-");
		advait.add("(3) Write a 'Thank-You' Diary :-");
		advait.add("(4) Strech it Out :-");
		advait.add("(5) Try Meditating :-");
		advait.add("(6) Book by Your Siden");
		advait.add("(7) Sleep is Crucial");

		List<String> shampoo = new ArrayList<String>();
		shampoo.add("Here are the hair types your shampoo must match with :- ");
		shampoo.add("(1) Rough or Dry Hair :- ");
		shampoo.add("(2) Oily Hair :-"+ "A non-creamy and non");
		shampoo.add("(3) Thin or Fine Hair :-");
		shampoo.add("(4) Wavy/Curly Hair :-");
		shampoo.add("(5) Chemically treated hair :-");

		List<String> fruits = new ArrayList<String>();
		fruits.add("Fruit is still full of great natural sugars and vitamins and in essence - is still healthy.If you are trying to lose weight, eating no more than 1-2 pieces of fruit a day plus loads of green vegetables.");
		fruits.add("Best Low Carb Fruits (LOW GI)");
		fruits.add("Berries (blueberries, raspberries)");
		fruits.add("Cherries");
		fruits.add("Plums");
		fruits.add("Grapefruit");
		fruits.add("Apple");
		fruits.add("Prunes");
		fruits.add("Dried apricots");
		fruits.add("Oranges");
		fruits.add("Worst High Carb Fruits (HIGH GI)");
		fruits.add("Banana");
		
		List<String> eggs = new ArrayList<String>();
		eggs.add("Here are 6 reasons why eggs are among the healthiest foods on the planet.");
		eggs.add("1. Whole Eggs Are Among The Most Nutritious Foods on Earth");
		eggs.add("2. Eggs Improve Your Cholesterol Profile and do NOT Raise Your Risk of Cardiovascular Disease");
		eggs.add("3. Eggs Are Loaded With Choline, an Important Nutrient For The Brain");
		eggs.add("4. Eggs Contain High Quality Proteins With a Perfect Amino Acid Profile");
		eggs.add("5. Eggs Are Loaded With Lutein and Zeaxanthin, Which Protect The Eyes");
		eggs.add("6. Eggs For Breakfast Can Help You Lose Body Fat");
		
		List<String> water = new ArrayList<String>();
		water.add("The way in which you drink water can seriously be a life-changer. And all you need to make is simple, minor changes such as --");
		water.add("1. First off, sit down to drink"+"(just as you should sit down to eat).");
		water.add("2. Take sips, not full-glass chugs. Small sip, swallow, breathe. Repeat.");
		water.add("3. Sip water throughout the day. If you chug too much water at once your body doesn't actually absorb all of it. Most of it will run right through you.");
		water.add("4. Drink at least room temperature water. Warm is even better.Cold and iced water literally douse the digestive fire.");
		water.add("5. For the same reason, don’t drink loads of water before or after your meals.");
		water.add("6. Your lips are yet another indicator. If they’re dry you might be dehydrated");
		
		List<String> firstAid = new ArrayList<String>();
		firstAid.add(" We need to learn to practice emotional first aid. Here are 7 ways to do so:");
		firstAid.add("1. Pay attention to Emotional Pain and respect it: The body has a way of telling that something is wrong and needs to be taken care. We get pain, fever, headache etc. ");
		firstAid.add("2.Redirect your gut reaction when you fail: The nature of psychological wounds makes it easy for one to lead to another. Failure can often drive you to focus on what you can’t do instead of focusing on what you can. ");
		firstAid.add("3.Monitor and protect your self-esteem: When you feel like putting yourself down, take a moment to be compassionate to yourself.");
		firstAid.add("4.When negative thoughts are taking over, disrupt them with positive distraction:");
		firstAid.add("5. Find meaning in loss: Loss is a part of life, but it can scar us and keep us from moving forward if we don’t treat the emotional wounds it creates.");
		firstAid.add("6.Don’t let excessive guilt linger: Guilt can be useful. In small doses, it alerts you to take action to mend a problem in your relationship with another person.");
		firstAid.add("7.Learn what treatments for emotional wounds work for you: Pay attention to yourself and learn how you, personally, deal with common emotional wounds. For instance, do you shrug them off, get really upset but recover quickly, get upset and recover slowly, supress your feelings, or …? ");
		
		List<String> germs = new ArrayList<String>();
		germs.add("The human immune system adapts to different attackers, which is why our species has survived for so long. When our bodies come into contact with a foreign substance, the immune system attacks it and retains this information. If this particular foreign substance comes back, the immune system knows what to do. This action can be seen most commonly with measles: a single infection is usually enough to protect a person for life.");
		
		List<String> coconut = new ArrayList<String>();
		coconut.add("Weight reduction: Coconut water has sufficient energy and again ended up being extremely adequate for getting thinner. This beverage has been seen as one of the healthiest approaches to get more fit as it is a characteristic drink and holds no chemicals whatsoever. Coconut water has been utilized for a long time everywhere throughout the planet for weight reduction and has demonstrated positive outcomes. In addition it is amazingly tasteful, so you can savor the integrity of coconut water as an unanticipated morning juice.");
		
		List<String> sleep = new ArrayList<String>();
		sleep.add("It also makes you look tired, sad, and certainly older. No wonder a sound night’s sleep is termed 'beauty sleep' Here are top 3 benefits of getting a beauty sleep-");
		sleep.add("1) Curbs wear and tear: Your skin, and your whole body, retire into recovery or repair mode while you are asleep.");
		sleep.add("2) Does balancing act: Sleep time is also the time when all kinds of hormonal and metabolic changes happen in the body, including your skin. As you snooze, your body churns out human growth hormone");
		sleep.add("3) It's recovery time: When you're tired, blood doesn't flow efficiently. The good thing about the skin is that it recovers quickly.");
		
		
		
		
		listDataChild.put(listDataHeader.get(0), advait); // Header, Child data
		listDataChild.put(listDataHeader.get(1), shampoo);
		listDataChild.put(listDataHeader.get(2), fruits);
		listDataChild.put(listDataHeader.get(3), eggs);
		listDataChild.put(listDataHeader.get(4), water);
		listDataChild.put(listDataHeader.get(5), firstAid);
		listDataChild.put(listDataHeader.get(6), germs);
		listDataChild.put(listDataHeader.get(7), coconut);
		listDataChild.put(listDataHeader.get(8), sleep);
		
		
	}

	
}
