import { Component } from '@angular/core';
import { SkillService } from '../skill.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrl: './skill-list.component.css'
})
export class SkillListComponent {

  skills: any=[];
  searchSkillname:any;
  copySkill:any=[];
  startIndex: number = 0;
  endIndex: number = 10;
  totalPages: number = 0;
  currentPage: number = 0;
  constructor(private skillService: SkillService, private router: Router) { }

  ngOnInit(): void {
    this.loadSkills();
  }

  loadSkills() {
    this.skillService.getAllSkills().subscribe(data => {
      this.skills = data;
      this.copySkill=data;
      this.calculateTotalPages();
        this.calculateCurrentPage();
    });
  }
  editSkill(skill: any) {
    this.router.navigate(['/edit-skill', skill.id]);
  }

  deleteSkill(skill: any) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette compétence?')) {
      this.skillService.deleteSkill(skill.id).subscribe(() => {
        this.loadSkills();
      });
    }
  }

  filteredSkills(){
    this.skills=this.copySkill;
    this.skills=this.skills.filter((skill:{name:string})=>
    
      skill.name.toLocaleLowerCase().includes(this.searchSkillname.toLocaleLowerCase())
    
    );
    this.calculateTotalPages();
        this.calculateCurrentPage();
        this.getItems()
  }


  getItems(): any[] {
    return this.skills.slice(this.startIndex, this.endIndex);
  }

 
  nextPage() {
    this.startIndex += 10;
    this.endIndex += 10;
    this.calculateCurrentPage();
    
  }

  prevPage() {
    this.startIndex -= 10;
    this.endIndex -= 10;
    this.calculateCurrentPage();
  }

  calculateTotalPages() {
    this.totalPages = Math.ceil(this.skills.length / 10);
  }

  calculateCurrentPage() {
    const elementsPerPage = 10; 
    this.currentPage = Math.min(Math.ceil(this.endIndex / elementsPerPage), Math.ceil(this.skills.length / elementsPerPage));
  }

}
