import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';


@Component({
  selector: 'app-list-formation',
  templateUrl: './list-formation.component.html',
  styleUrl: './list-formation.component.css'
})
export class ListFormationComponent implements OnInit{

  constructor(private userService: UserServiceService){}
  formation:any;
  ngOnInit(): void {
    
    const username = sessionStorage.getItem('username')
    this.userService.HisFormation(username).subscribe(
      res=>{
        this.formation=res;
        console.log(res);
      },
      err=>{
        console.log(err);
      }
    );
  }

}
