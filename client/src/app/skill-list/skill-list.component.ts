import { Component, OnInit } from '@angular/core';

import { SkillService } from '../shared/skill/skill.service';
import { NONE_TYPE } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.css']
})
export class SkillListComponent implements OnInit {

  headers = ["Proficient", "Bonus", "Skill", "Modifier"];

  rows = [
    {
      "Proficient": true,
      "Bonus": "-1",
      "Skill": "Acrobatics",
      "Modifier": "Dexterity"
    },
    {
      "Proficient": false,
      "Bonus": "+2",
      "Skill": "Animal Handling",
      "Modifier": "Wisdom"
    },
    {
      "Proficient": false,
      "Bonus": "+2",
      "Skill": "Arcana",
      "Modifier": "Intelligence"
    },
    {
      "Proficient": false,
      "Bonus": "+5",
      "Skill": "Athletics",
      "Modifier": "Strength"
    },
    {
      "Proficient": false,
      "Bonus": "+2",
      "Skill": "Deception",
      "Modifier": "Wisdom"
    },
    {
      "Proficient": false,
      "Bonus": "-1",
      "Skill": "History",
      "Modifier": "Intelligence"
    }
  ]

// skills: Array<any>;

// constructor(private skillService: SkillService) { }

//   ngOnInit() {
//     this.skillService.getAllSkills().subscribe(data => {
//       this.skills = data
//     });
//   }

}
