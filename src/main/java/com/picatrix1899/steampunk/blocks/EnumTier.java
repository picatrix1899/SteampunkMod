package com.picatrix1899.steampunk.blocks;

import net.minecraft.util.IStringSerializable;
import scala.actors.threadpool.Arrays;

public enum EnumTier implements IStringSerializable
{
	TIER_1("tier1", "t1"),
	TIER_2("tier2", "t2"),
	TIER_3("tier3", "t3")
	;

	private String name;
	private String shortName;
	
	private EnumTier(String name, String shortName)
	{
		this.name = name;
		this.shortName = shortName;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public String getShortName()
	{
		return this.shortName;
	}
	
	public String toString()
	{
		return this.name;
	}

	public int getIndex()
	{
		return ordinal();
	}
	
	public static EnumTier[] getTierList(int maxTier)
	{
		return (EnumTier[])Arrays.copyOfRange(values(), 0, maxTier);
	}
}
