package com.example.demo.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Playlist;
import com.example.demo.repositories.PlayListRepository;
@Service
public class Playlistserviceimplementation implements PlayListService{
	@Autowired
	PlayListRepository pr;

	@Override
	public void addPlaylist(Playlist playlist) {
		pr.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

	

	
}
