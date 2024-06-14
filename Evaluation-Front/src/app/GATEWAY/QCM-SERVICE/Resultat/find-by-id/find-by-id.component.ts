import { Component, OnInit } from '@angular/core';
import { ReponseServiceService } from '../reponse-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-find-by-id',
  templateUrl: './find-by-id.component.html',
  styleUrl: './find-by-id.component.css'
})
export class FindByIdComponent implements OnInit{

  constructor(private service : ReponseServiceService, private router : Router, private route : ActivatedRoute){}
  id:any;
  resultat: any = {
    quiz: {
      title: '',
      category: '',
      difficultylevel: '',
      minimumSuccessPercentage: 0
    },
    resultat: {
      score: 0,
      percentage: 0,
      resultat: false
    },
    responses: []
  };
  ngOnInit(): void {
    this.route.params.subscribe(p=>{this.id=p['id']});
    this.service.getById(this.id).subscribe(
      res=>{
        this.resultat=res;
        console.log(res);
      },
      err=>{
        console.log(err)
      }
    );
  }

}
