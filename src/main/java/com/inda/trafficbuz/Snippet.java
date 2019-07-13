package com.inda.trafficbuz;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Snippet {
	public static void main(String args[])
	{
	String encoded=new BCryptPasswordEncoder().encode("pass");
	System.out.println(encoded);
};
}

