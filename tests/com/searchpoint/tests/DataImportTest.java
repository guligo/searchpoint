package com.searchpoint.tests;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.searchpoint.entities.Category;
import com.searchpoint.entities.Company;
import com.searchpoint.entities.Currency;
import com.searchpoint.entities.Item;
import com.searchpoint.entities.Status;
import com.searchpoint.entities.User;
import com.searchpoint.services.CategoryService;
import com.searchpoint.services.CompanyService;
import com.searchpoint.services.DataBaseService;
import com.searchpoint.services.ItemService;
import com.searchpoint.services.SearchStatisticsService;
import com.searchpoint.services.UserService;

/**
 * Run to import data into database.
 * 
 * @author guligo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("resources/test-services.xml")
public class DataImportTest extends TestCase {	
	
	private final int N = 10;
	private final int M = 10;
	private final int C = 50;
	
	@Autowired
	private DataBaseService dataService;	
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SearchStatisticsService searchService;
	
	@Override
	protected void setUp() throws Exception {
		dataService.cleanDatabase();
	}
	
	@Test
	public void testDummy() {
		/* do nothing */
	}
	
	@Test
	public void testDataImport() {
		/* create few records in search statistics table */
		searchService.addSearch("qwerty");
		searchService.addSearch("foobar");
		searchService.addSearch("asdfgr");
		
		/* create user */
		User user = new User();	
		user.setEmail("test@test.test");
		user.setPassword("test");		
		userService.addUser(user);
		
		/* create companies */
		Company company = null;
		for (int i = 1; i <= C; i++) {
			company = new Company();
			company.setTitle("Company " + i);
			company.setHomepageUrl("http://homepage-url-" + i);
			company.setLogoUrl("css/searchpoint/icons/image.png");
			company.setUser(user);
			company.setStatus(Status.COMPANY_STATUS_OK);
			companyService.saveCompany(company);
		}		
		
		/* TODO: improve design */
		/* production data */
		Category parent = null;
		Category child = null;
		
		/* category #1 */
		parent = new Category();
		parent.setName("Art & Collectibles");
		parent.setImageUrl("css/searchpoint/icons/categories/art.png");
		categoryService.addCategory(parent);
		
		String names[] = new String[] {"Antiques", "Clocks", "Coins",
			"Collectables", "Crafts", "Ethnic",
			"Figurines", "Glass", "Painting & Prints",
			"Religious"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);			
			categoryService.addCategory(child);
		}
		/* END! category #1 */
		
		/* category #2 */
		parent = new Category();
		parent.setName("Automotive");
		parent.setImageUrl("css/searchpoint/icons/categories/auto.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Accessories & Parts", "Insurance & Loans", "Motorcycles",
			"New & Used Cars", "Scale Models"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #2 */
		
		/* category #3 */
		parent = new Category();
		parent.setName("Beauty & Fragrances");
		parent.setImageUrl("css/searchpoint/icons/categories/fragrance.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Bath & Body", "Cosmetics", "Fragrances",
			"Hair & Skin Care"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #3 */
		
		/* category #4 */
		parent = new Category();
		parent.setName("Books & Magazines");
		parent.setImageUrl("css/searchpoint/icons/categories/book.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"For Children", "E-Books", "General",
			"Magazines", "Used"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #4 */
		
		/* category #5 */
		parent = new Category();
		parent.setName("Clothing & Shoes");
		parent.setImageUrl("css/searchpoint/icons/categories/clothing.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Accessories", "For Children", "General",
			"Hats", "Lingerie", "Men", "Shoes", "Women"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #5 */
		
		/* category #6 */
		parent = new Category();
		parent.setName("Computers");
		parent.setImageUrl("css/searchpoint/icons/categories/computer.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Hardware", "Hosting", "Internet",
			"Marketing", "Search Optimization", "Services",
			"Software", "Web Design"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #6 */
		
		/* category #7 */
		parent = new Category();
		parent.setName("Electronics");
		parent.setImageUrl("css/searchpoint/icons/categories/electronics.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Audio", "Cameras", "Communications",
			"General", "Video"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #7 */
		
		/* category #8 */
		parent = new Category();
		parent.setName("Fitness & Health");
		parent.setImageUrl("css/searchpoint/icons/categories/health.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Fitness Equipment", "Health Insurance", "Hearing & Vision",
			"Medical Equipment", "Men's Health", "Pharmaceuticals",
			"Self Improvement", "Sexuality", "Spirituality",
			"Vitamins & Supplements"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #8 */
		
		/* category #9 */
		parent = new Category();
		parent.setName("Gifts & Flowers");
		parent.setImageUrl("css/searchpoint/icons/categories/gift.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"All Occasions", "Flowers", "Gift Baskets",
			"Gift Ideas", "Personalized Gifts"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #9 */
		
		/* category #10 */
		parent = new Category();
		parent.setName("Home & Garden");
		parent.setImageUrl("css/searchpoint/icons/categories/home.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Bathroom", "Electrical", "Furniture",
			"Garden", "Hobbies", "Home Decor",
			"Safety", "Services"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #10 */
		
		/* category #11 */
		parent = new Category();
		parent.setName("Jewelry & Watches");
		parent.setImageUrl("css/searchpoint/icons/categories/watch.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Charms", "Diamonds & Gems", "Handmade",
			"Piercing", "Precious Metals", "Watches"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #11 */
		
		/* category #12 */
		parent = new Category();
		parent.setName("Kitchen & Gourmet");
		parent.setImageUrl("css/searchpoint/icons/categories/kitchen.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Beverages", "Cookware", "Food",
			"Tableware", "Wine"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #12 */
		
		/* category #13 */
		parent = new Category();
		parent.setName("Luxury");
		parent.setImageUrl("css/searchpoint/icons/categories/lux.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Fashion Items", "Luxury Goods"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #13 */
		
		/* category #14 */
		parent = new Category();
		parent.setName("Maternity & Baby");
		parent.setImageUrl("css/searchpoint/icons/categories/baby.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Baby Clothing", "Baby Toys", "General",
			"Maternity Clothing", "Nursing and Feeding"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #14 */
		
		/* category #15 */
		parent = new Category();
		parent.setName("Music & Movies");
		parent.setImageUrl("css/searchpoint/icons/categories/music.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"General", "Movies", "Music"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #15 */
		
		/* category #16 */
		parent = new Category();
		parent.setName("Office Products");
		parent.setImageUrl("css/searchpoint/icons/categories/office.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"General", "Office Equipment", "Office Furniture",
			"Office Supplies"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #16 */

		/* category #17 */
		parent = new Category();
		parent.setName("Pets");
		parent.setImageUrl("css/searchpoint/icons/categories/pet.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Cats", "Dogs", "Fish",
			"General", "Horses", "Reptiles"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #17 */
		
		/* category #18 */
		parent = new Category();
		parent.setName("Sports & Outdoors");
		parent.setImageUrl("css/searchpoint/icons/categories/sport.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Equipment", "Fishing", "Hunting",
			"Sports Memorabilia", "Sportswear", "Tickets"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #18 */
		
		/* category #19 */
		parent = new Category();
		parent.setName("Toys & Games");
		parent.setImageUrl("css/searchpoint/icons/categories/toys.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Collectible Cards", "Dolls & Plush", "Educational",
			"Gambling", "General", "Outdoor",
			"Puzzles", "Video Games"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #19 */
		
		/* category #20 */
		parent = new Category();
		parent.setName("Travel");
		parent.setImageUrl("css/searchpoint/icons/categories/travel.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"General", "Travel Agencies", "Travel Goods"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #20 */
		
		/* category #21 */
		parent = new Category();
		parent.setName("Wedding");
		parent.setImageUrl("css/searchpoint/icons/categories/wedding.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Accessories", "Bridal Gowns", "Favors",
			"General", "Gifts", "Services"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #21 */
		
		/* category #22 */
		parent = new Category();
		parent.setName("Cell Phones & PDAs");
		parent.setImageUrl("css/searchpoint/icons/categories/phone.png");
		categoryService.addCategory(parent);
		
		names = new String[] {"Cell Phones & Smartphones", "Cell Phone & PDA Accessories", "Display Phones",
			"PDAs & Pocket PCs", "Phone Cards & SIM Cards", "Replacement Parts & Tools"};
		for (String name : names) {
			child = new Category();
			child.setParent(parent);
			child.setName(name);
			categoryService.addCategory(child);
		}
		/* END! category #22 */
		
		/* create categories */
		for (int i = 1; i <= N; i++) {
			/* create items */
			for (int j = 1; j <= M; j++) {
				Item item = new Item();
				item.setName("Item " + i + " " + j);
				item.setCategory(child);
				item.setCompany(company);
				item.setPrice(new Double(Math.round(Math.random() * 100)));
				item.setCurrency(Currency.GBP);				
				item.setInStock(Math.round(Math.random() * 10));
				item.setItemUrl("http://www.google.lv");
				itemService.saveItem(item);
			}
		}
	}
	
}
