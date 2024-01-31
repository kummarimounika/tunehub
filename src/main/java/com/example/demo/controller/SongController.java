package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.services.SongService;
@Controller
public class SongController {
	@Autowired
	SongService ss;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus=ss.songExits(song.getName());
		if(songStatus==false) {
			ss.addSong(song);
			System.out.println("song added sucessfully");
		}else {
			System.out.println("song alredy exists");

		}
		return "adminHome";
	}
@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songList=ss.fetchAllSongs();
		model.addAttribute("songs",songList);

		return "displaySong";
	}
@GetMapping("/playSongs")
public String playSongs(Model model) {
	boolean premiumUser=false;
	if(premiumUser==true) {
		
	List<Song> songList=ss.fetchAllSongs();
	model.addAttribute("songs",songList);

	return "displaySong";
}else {
	return "makePayment";
}
}
}
