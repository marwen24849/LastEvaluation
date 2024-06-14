import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SkillService } from '../skill.service';

@Component({
  selector: 'app-skill-update',
  templateUrl: './skill-update.component.html',
  styleUrl: './skill-update.component.css'
})
export class SkillUpdateComponent implements OnInit {
  skill: any = { id: null, name: '', description: '' };

  constructor(private route: ActivatedRoute, private router: Router, private skillService: SkillService) { }
  id:any;
  ngOnInit(): void {
    this.route.params.subscribe(res => {this.id = res['id']});
    this.skillService.getSkillById(this.id).subscribe(data => {
      this.skill = data;
    });
  }

  updateSkill() {
    this.skillService.updateSkill(this.skill.id, this.skill).subscribe(() => {
      this.router.navigate(['/skills']);
    });
  }

}
