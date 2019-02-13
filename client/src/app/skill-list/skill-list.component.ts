import { Component, OnInit } from '@angular/core';

import { SkillService } from '../shared/skill/skill.service';

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.css']
})
export class SkillListComponent implements OnInit {

  skills: Array<any>;

  constructor(private skillService: SkillService) { }

  ngOnInit() {
    this.skillService.getAllSkills().subscribe(data => {
      this.skills = data
    });
  }

}
