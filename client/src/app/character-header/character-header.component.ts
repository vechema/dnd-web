import { Component, OnInit } from '@angular/core';

import { CharacterHeader } from '../shared/character/character.service';

@Component({
  selector: 'character-header',
  templateUrl: './character-header.component.html',
  styleUrls: ['./character-header.component.css']
})
export class CharacterHeaderComponent implements OnInit {

  character: Object;

  constructor(private characterHeader: CharacterHeader) { }

  ngOnInit() {
    this.characterHeader.getCharacter().subscribe(data => {
      this.character = data
    });    

  }

}


