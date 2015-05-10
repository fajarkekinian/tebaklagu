package aboot.tebaklagu.manager;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Color;
import android.widget.EditText;
import android.widget.TableLayout;
import aboot.tebaklagu.Tebaklagu;

public class ResourceManager {

	private static final ResourceManager INSTANCE = new ResourceManager();
	
	public Engine engine;
	public Tebaklagu activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	
	//disable
	public ITextureRegion regiondisable;
	private BitmapTextureAtlas atlasdisable;
	
	
	
	
	
	/**
	 * 
	 * SOUND
	 * 
	 */
	public Music click;
	//SOUND
	private void loadAudio(){
		MusicFactory.setAssetBasePath("sound/");
		try {
			click = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity,"sound.mp3");		
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 
	 * FONT
	 * 
	 */
	public Font helvetica, Bubblegum;
	//FONT
	private void loadFontHelvetica(){
		FontFactory.setAssetBasePath("font/");
		
		textureFont = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512);
		helvetica = FontFactory.createFromAsset(activity.getFontManager(), textureFont, activity.getAssets(), "helvetica.ttf", 50, true, Color.WHITE);
		helvetica.load();
	}
	private void loadFontBubblegum(){
		FontFactory.setAssetBasePath("font/");
		
		textureFont2 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512);
		Bubblegum = FontFactory.createFromAsset(activity.getFontManager(), textureFont2, activity.getAssets(), "Bubblegum.ttf", 50, true, Color.WHITE);
		Bubblegum.load();
	}
	
	
	
	
	
	/**
	 * 
	 * SPLASH
	 * 
	 */
	public ITextureRegion regionSplash;
	private BitmapTextureAtlas atlasSplash;
	//SPLASH
	public void loadSplashScreen(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		
		atlasSplash = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionSplash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasSplash, activity, "mentilin.png",0,0);
		atlasSplash.load();
	}	
	public void unloadSplashScreen(){
		atlasSplash.unload();
		regionSplash = null;
	}
	
	
	
	
	
	/**
	 * 
	 * MENU
	 * 
	 */	 
	private ITexture textureFont, textureFont2;
	public ITextureRegion regionbtnmulai, regionbtncarabermain, regioncredit, regionsetting, regionbg, regionlogoa, regionlogob;
	private BitmapTextureAtlas atlasbtnmulai, atlasbtncarabermain,atlascredit, atlassetting, atlasbg, atlaslogoa, atlaslogob;
	//MENU
	public void loadMenuResource(){
		loadMenuGraphics();
		loadAudio();
	}
	private void loadMenuGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/menu/");
		
		atlasbtnmulai = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionbtnmulai = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasbtnmulai, activity, "mulai.png",0,0);
		atlasbtnmulai.load();
		
		atlasbtncarabermain = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionbtncarabermain = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasbtncarabermain, activity, "caramain.png",0,0);
		atlasbtncarabermain.load();
		
		atlascredit = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regioncredit = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlascredit, activity, "credit.png",0,0);
		atlascredit.load();
		
		atlassetting = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionsetting = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlassetting, activity, "setting.png",0,0);
		atlassetting.load();
		
		atlaslogoa = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionlogoa = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaslogoa, activity, "logoA.png",0,0);
		atlaslogoa.load();
		
		atlaslogob = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionlogob = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaslogob, activity, "logoB.png",0,0);
		atlaslogob.load();
		
		atlasbg = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.REPEATING_BILINEAR_PREMULTIPLYALPHA);
		regionbg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasbg, activity, "pat1.png",0,0);
		regionbg.setTextureWidth(camera.getWidth());
		regionbg.setTextureHeight(camera.getHeight());
		atlasbg.load();
	}
	public void unloadMenuGraphics(){
		atlasbtnmulai.unload();
		atlasbtncarabermain.unload();
		atlascredit.unload();
		atlassetting.unload();
		atlaslogoa.unload();
		atlaslogob.unload();
		atlasbg.unload();
	}
	
	
	
	
	
	/**
	 * 
	 * CATEGORY
	 * 
	 */

	public ITextureRegion regioncate, regionpop, regionrock, regiondangdut, regiondaerah;
	private BitmapTextureAtlas atlascate, atlaspop, atlasrock, atlasdangdut, atlasdaerah;
	//CATEGORY
	public void loadCategoryResource(){
		loadCategoryGraphics();
		loadAudio();
	}
	private void loadCategoryGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/category/");
		
		atlascate = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regioncate = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlascate, activity, "category.png",0,0);
		atlascate.load();
		
		atlaspop = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionpop = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaspop, activity, "pop.png",0,0);
		atlaspop.load();
		
		atlasrock = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionrock = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasrock, activity, "rock.png",0,0);
		atlasrock.load();
		
		atlasdangdut = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondangdut = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdangdut, activity, "dangdut.png",0,0);
		atlasdangdut.load();
		
		atlasdaerah = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondaerah = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdaerah, activity, "daerah.png",0,0);
		atlasdaerah.load();
	}
	public void unloadCategoryGraphics(){
		atlascate.unload();
		atlaspop.unload();
		atlasrock.unload();
		atlasdangdut.unload();
		atlasdaerah.unload();
	}
	
	
	
	
	
	/**
	 * 
	 * POP
	 * 
	 */
	public ITextureRegion regionpophead, regionpop1, regionpop2, regionpop3, regionpop4;
	private BitmapTextureAtlas atlaspophead, atlaspop1, atlaspop2, atlaspop3, atlaspop4;
	//POP
	public void loadPopResource(){
		loadPOPGraphics();
		loadAudio();
		loadFontBubblegum();
		loadFontHelvetica();
	}
	private void loadPOPGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/category/pop/");
		
		atlaspophead = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionpophead = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaspophead, activity, "pophead.png",0,0);
		atlaspophead.load();
		
		atlaspop1 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionpop1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaspop1, activity, "poplevel1.png",0,0);
		atlaspop1.load();
		
		atlaspop2 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionpop2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaspop2, activity, "poplevel2.png",0,0);
		atlaspop2.load();
		
		atlaspop3 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionpop3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaspop3, activity, "poplevel3.png",0,0);
		atlaspop3.load();
		
		atlaspop4 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionpop4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaspop4, activity, "poplevel4.png",0,0);
		atlaspop4.load();
		
		atlasdisable = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondisable = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdisable, activity, "disable.png",0,0);
		atlasdisable.load();
	}
	public void unloadPOPGraphics(){
		atlaspophead.unload();
		atlaspop1.unload();
		atlaspop2.unload();
		atlaspop3.unload();
		atlaspop4.unload();
	}
	
	
	
	
	
	/**
	 * 
	 * ROCK
	 * 
	 */
	public ITextureRegion regionrockhead, regionrock1, regionrock2, regionrock3, regionrock4;
	private BitmapTextureAtlas atlasrockhead, atlasrock1, atlasrock2, atlasrock3, atlasrock4;
	//ROCK
	public void loadRockResource(){
		loadROCKGraphics();
		loadAudio();
		loadFontBubblegum();
		loadFontHelvetica();
	}
	private void loadROCKGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/category/rock/");
		
		atlasrockhead = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionrockhead = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasrockhead, activity, "rockhead.png",0,0);
		atlasrockhead.load();
		
		atlasrock1 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionrock1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasrock1, activity, "rocklevel1.png",0,0);
		atlasrock1.load();
		
		atlasrock2 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionrock2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasrock2, activity, "rocklevel2.png",0,0);
		atlasrock2.load();
		
		atlasrock3 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionrock3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasrock3, activity, "rocklevel3.png",0,0);
		atlasrock3.load();
		
		atlasrock4 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionrock4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasrock4, activity, "rocklevel4.png",0,0);
		atlasrock4.load();
		
		atlasdisable = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondisable = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdisable, activity, "disable.png",0,0);
		atlasdisable.load();
	}
	public void unloadROCKGraphics(){
		atlasrockhead.unload();
		atlasrock1.unload();
		atlasrock2.unload();
		atlasrock3.unload();
		atlasrock4.unload();
	}
	
	/**
	 * 
	 * DANGDUT
	 * 
	 */
	public ITextureRegion regionduthead, regiondut1, regiondut2, regiondut3, regiondut4;
	private BitmapTextureAtlas atlasduthead, atlasdut1, atlasdut2, atlasdut3, atlasdut4;
	//DANGDUT
	public void loadDangdutResource(){
		loadDANGDUTGraphics();
		loadAudio();
		loadFontBubblegum();
		loadFontHelvetica();
	}
	private void loadDANGDUTGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/category/dangdut/");
		
		atlasduthead = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionduthead = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasduthead, activity, "dangduthead.png",0,0);
		atlasduthead.load();
		
		atlasdut1 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondut1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdut1, activity, "dangdutlevel1.png",0,0);
		atlasdut1.load();
		
		atlasdut2 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondut2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdut2, activity, "dangdutlevel2.png",0,0);
		atlasdut2.load();
		
		atlasdut3 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondut3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdut3, activity, "dangdutlevel3.png",0,0);
		atlasdut3.load();
		
		atlasdut4 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondut4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdut4, activity, "dangdutlevel4.png",0,0);
		atlasdut4.load();
		
		atlasdisable = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondisable = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdisable, activity, "disable.png",0,0);
		atlasdisable.load();
	}
	public void unloadDANGDUTGraphics(){
		atlasduthead.unload();
		atlasdut1.unload();
		atlasdut2.unload();
		atlasdut3.unload();
		atlasdut4.unload();
	}
	
	
	
	
	
	/**
	 * 
	 * DAERAH
	 * 
	 */
	public ITextureRegion regiondaerahhead, regiondaerah1, regiondaerah2, regiondaerah3, regiondaerah4;
	private BitmapTextureAtlas atlasdaerahhead, atlasdaerah1, atlasdaerah2, atlasdaerah3, atlasdaerah4;
	//DAERAH
	public void loadDaerahResource(){
		loadDAERAHGraphics();
		loadAudio();
		loadFontBubblegum();
		loadFontHelvetica();
	}
	private void loadDAERAHGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/category/daerah/");
		
		atlasdaerahhead = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondaerahhead = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdaerahhead, activity, "daerahhead.png",0,0);
		atlasdaerahhead.load();
		
		atlasdaerah1 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondaerah1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdaerah1, activity, "daerahlevel1.png",0,0);
		atlasdaerah1.load();
		
		atlasdaerah2 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondaerah2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdaerah2, activity, "daerahlevel2.png",0,0);
		atlasdaerah2.load();
		
		atlasdaerah3 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondaerah3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdaerah3, activity, "daerahlevel3.png",0,0);
		atlasdaerah3.load();
		
		atlasdaerah4 = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondaerah4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdaerah4, activity, "daerahlevel4.png",0,0);
		atlasdaerah4.load();
		
		atlasdisable = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiondisable = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasdisable, activity, "disable.png",0,0);
		atlasdisable.load();
	}
	public void unloadDAERAHGraphics(){
		atlasdaerahhead.unload();
		atlasdaerah1.unload();
		atlasdaerah2.unload();
		atlasdaerah3.unload();
		atlasdaerah4.unload();
	}
	
	
	
	
	
	/**
	 * 
	 * GAME
	 * 
	 */	
	public ITextureRegion regionplay, regioncheck, regionDj, regionDisk, regiongagang, regionkomplit, regionkomplitb, regionback;
	private BitmapTextureAtlas atlasplay, atlascheck, atlasDj, atlasDisk, atlasgagang, atlaskomplit, atlaskomplitb, atlasback;
	public EditText editText;
	public TableLayout tableLayout;
	private BitmapTextureAtlas atlasnyawa;
	public ITiledTextureRegion regionnyawa;
	//game
	public void loadGAMEGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/game/");
		
		atlasplay = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionplay = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasplay, activity, "replay.png",0,0);
		atlasplay.load();
		
		atlascheck = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regioncheck = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlascheck, activity, "check.png",0,0);
		atlascheck.load();
		
		atlasDj = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionDj = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasDj , activity, "dj.png",0,0);
		atlasDj.load();
		
		atlasDisk = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionDisk = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasDisk, activity, "disk.png",0,0);
		atlasDisk.load();
		
		atlasgagang = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regiongagang = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasgagang , activity, "gagang.png",0,0);	
		atlasgagang.load();
		
		atlaskomplit = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionkomplit = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaskomplit , activity, "levelkomplita.png",0,0);	
		atlaskomplit.load();
		
		atlaskomplitb = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionkomplitb = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlaskomplitb , activity, "levelkomplitb.png",0,0);	
		atlaskomplitb.load();
		
		atlasback = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionback = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasback, activity, "back.png",0,0);
		atlasback.load();
		
		atlasnyawa = new BitmapTextureAtlas(engine.getTextureManager(), 300, 300, TextureOptions.BILINEAR);
		regionnyawa = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(atlasnyawa, activity, "nyawas.png", 0, 0, 1, 4);
		atlasnyawa.load();
	}
	public void unloadGAMEGraphics(){
		atlasplay.unload();
		atlascheck.unload();
		atlasDj.unload();
		atlasDisk.unload();
		atlasgagang.unload();
		atlaskomplit.unload();
		atlaskomplitb.unload();
		atlasback.unload();
	}
	public TableLayout editText() {
        editText = new EditText(activity);
        editText.setHint("Judul Lagu");
        editText.setMinHeight(80);
        editText.setTextSize(20);
        editText.setBackgroundColor(Color.WHITE);
        tableLayout = new TableLayout(activity);
        tableLayout.addView(editText);
        return tableLayout;
    }

	/**
	 * 
	 * BENAR
	 * 
	 */
	public ITextureRegion regionBenar, regionBenarbiru, regionBenarMerah, regionnext;
	private BitmapTextureAtlas atlasBenar, atlasBenarbiru, atlasBenarmerah, atlasnext;
	//BENAR
	public void loadBENARGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/benar/");
		
		atlasBenar = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionBenar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasBenar, activity, "benar.png",0,0);
		atlasBenar.load();
		
		atlasBenarbiru = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionBenarbiru = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasBenarbiru, activity, "benarbiru.png",0,0);
		atlasBenarbiru.load();
		
		atlasBenarmerah = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionBenarMerah = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasBenarmerah, activity, "benarmerah.png",0,0);
		atlasBenarmerah.load();
		
		atlasnext = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionnext = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasnext, activity, "next.png",0,0);
		atlasnext.load();
	}
	public void unloadBENARGraphics(){
		atlasBenar.unload();
		atlasBenarbiru.unload();
		atlasBenarmerah.unload();
		atlasnext.unload();
	}
	
	
	
	
	
	/**
	 * 
	 * CARA
	 * 
	 */
	public ITextureRegion regionCarahead, regionCara, regionCredithead, regionCredit;
	private BitmapTextureAtlas atlasCarahead, atlasCara, atlasCredithead, atlasCredit;
	//CARA
	public void loadCARAGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/other/");
		
		atlasCarahead = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionCarahead = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasCarahead, activity, "caramain.png",0,0);
		atlasCarahead.load();
		
		atlasCara = new BitmapTextureAtlas(activity.getTextureManager(), 512, 728, TextureOptions.BILINEAR);
		regionCara = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasCara, activity, "CARA MAIN.png",0,0);
		atlasCara.load();
		
		atlasCredithead = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionCredithead = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasCredithead, activity, "credithead.png",0,0);
		atlasCredithead.load();
		
		atlasCredit = new BitmapTextureAtlas(activity.getTextureManager(), 512, 728, TextureOptions.BILINEAR);
		regionCredit = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasCredit, activity, "credit.png",0,0);
		atlasCredit.load();
	}
	public void unLoadCARAGraphics(){
		atlasCarahead.unload();
		atlasCara.unload();
		atlasCredithead.unload();
		atlasCredit.unload();
	}
	
	
	
	
	/**
	 * 
	 * SETTING
	 * 
	 */
	public ITextureRegion regionheadsetting, regionresetpop, regionresetrock, regionresetdangdut, regionresetdaerah;
	private BitmapTextureAtlas atlasheadsetting, atlasresetpop, atlasresetrock, atlasresetdangdut, atlasresetdaerah;
	//SETTING
	public void loadSettingResource(){
		loadSETTINGGraphics();
		loadAudio();
	}
	private void loadSETTINGGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/other/");
		
		atlasheadsetting = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionheadsetting = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasheadsetting, activity, "settinghead.png",0,0);
		atlasheadsetting.load();
		
		atlasresetpop = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionresetpop = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasresetpop, activity, "resetpop.png",0,0);
		atlasresetpop.load();
		
		atlasresetrock = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionresetrock = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasresetrock, activity, "resetrock.png",0,0);
		atlasresetrock.load();
		
		atlasresetdangdut = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionresetdangdut = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasresetdangdut, activity, "resetdangdut.png",0,0);
		atlasresetdangdut.load();
		
		atlasresetdaerah = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		regionresetdaerah = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlasresetdaerah, activity, "resetdaerah.png",0,0);
		atlasresetdaerah.load();
	}
	public void unloadSettinggraphics(){
		atlasresetpop.unload();
		atlasresetrock.unload();
		atlasresetdangdut.unload();
		atlasresetdaerah.unload();
	}
	
	
	
	
	
	public static void prepareManager(Engine engine, Tebaklagu activity, Camera camera, VertexBufferObjectManager vbom){
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}
	public static ResourceManager getInstance(){
		return INSTANCE;
	}
}
