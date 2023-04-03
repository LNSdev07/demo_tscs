import { Component, OnInit } from '@angular/core';
import { Paginator } from '../common/paginator';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss']
})
export class UserManagementComponent implements OnInit {
  
  users: any
  totalRecords = 0;

  paginator: Paginator ={
    page: 1,
    pageSize: 5,
    sortColumn: "",
    condition: "asc"
  }

  constructor(private userService: UserService){}

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }


  reloadTable(e: any){
    this.paginator.page = e.first==0? 1: (e.first/e.rows +1);
    this.paginator.pageSize = e.rows;
    this.paginator.sortColumn = e.sortField;
    this.paginator.condition = e.sortOrder === 1 ? 'desc' : 'asc';
    
    console.log(this.paginator)
    this.getData();
  }

  refresh(){
    this.paginator.page = 1;
    this.paginator.pageSize = 5;
    this.paginator.sortColumn = "username"
    this.paginator.condition = "desc"
  
    this.getData();
  }
 
  getData(){
    this.userService.findUser(this.paginator).subscribe(res =>{
          this.users = res.data;
          this.totalRecords = res.totalRecords;

          console.log(this.users)
    })
  }
      
} 
