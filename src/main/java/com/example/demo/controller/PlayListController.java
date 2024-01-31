package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;

@Controller
public class PlayListController {
	@Autowired
	SongService ss;
@Autowired
PlayListService pls;
@GetMapping("/createPlaylist")
public String createPlaylist(Model model) {
	List<Song> songList=ss.fetchAllSongs();
	System.out.println(songList);
	model.addAttribute("songs",songList);
	return "createPlayList";
}
@PostMapping("/addPlaylist")
public String addPlaylist(@ModelAttribute Playlist playlist) {
	pls.addPlaylist(playlist);
	System.out.println(playlist); 
	List<Song> songList=playlist.getSongs();
	for(Song s:songList) {
		s.getPlay().add(playlist);
		ss.updateSong(s);
	}
	return "adminHome";
}
@GetMapping("/viewPlaylist")
public String viewPlaylist(Model model) {
	List<Playlist> allPlaylist=pls.fetchAllPlaylists();
	model.addAttribute("allPlaylist",allPlaylist);
	return "displayPlayList";
}
}
