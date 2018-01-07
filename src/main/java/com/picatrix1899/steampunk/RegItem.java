package com.picatrix1899.steampunk;

import net.minecraft.item.Item;

public class RegItem
{
	private final Item item;
	
	private int meta = 0;
	private String variant = "inventory";
	
	private String name = "";
	private boolean hasName = false;
	
	private String modPrefix = "steampunk";
	private boolean hasModPrefix = true;
	
	private String path = "";
	private boolean hasPath = false;
	
	public RegItem(Item item)
	{
		this.item = item;
	}
	
	public RegItem meta(int meta) { this.meta = meta; return this; }
	
	public RegItem variant(String variant) { this.variant = variant; return this; }
	
	public RegItem name(String name) { this.name = name; return this; }
	public RegItem hasName(boolean b) { this.hasName = b; return this; }
	
	public RegItem modPrefix(String modPrefix) { this.modPrefix = modPrefix; return this; }
	public RegItem hasModPrefix(boolean b) { this.hasModPrefix = b; return this; }
	
	public RegItem path(String path) { this.path = path.endsWith("/") ? path : path + "/"; return this; }
	public RegItem hasPath(boolean b) { this.hasPath = b; return this; }
	
	public String name() { return this.name; }
	public boolean hasName() { return this.hasName; }
	
	public String modPrefix() { return this.modPrefix; }
	public boolean hasModPrefix() { return this.hasModPrefix; }
	
	public String path() { return this.path; }
	public boolean hasPath() { return this.hasPath; }
	
	public String varaint() { return this.variant; }
	
	public int meta() { return this.meta; }
	
	public Item item() { return this.item; }
	
	
	
	public void reset()
	{
		this.meta = 0;
		this.variant = "inventory";
		this.name = "";
		this.hasName = false;
		this.modPrefix = "steampunk";
		this.hasModPrefix = true;
		this.path = "";
		this.hasPath = false;
	}
	
	public String getCanonicalName()
	{
		String name = "";
		
		name += this.hasModPrefix ? this.modPrefix + ":" : "";
		name += this.hasPath ? this.path : "";
		name += this.hasName ? this.name : getUnlocalizedName(this.item);
		
		return name;
	}
	
	private static String getUnlocalizedName(Item item)
	{
		return item.getUnlocalizedName().substring(5);
	}
}
