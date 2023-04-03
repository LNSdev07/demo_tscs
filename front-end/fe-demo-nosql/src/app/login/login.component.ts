import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminDTO } from './model/AdminDTO.model';
import { LoginServiceService } from './service/login-service.service';
import { TokenService } from '../common/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
     FormLogin!: FormGroup  

     constructor(private fb: FormBuilder,
                 private loginService: LoginServiceService,
                 private tokenService: TokenService,
                 private router: Router){

     }

     ngOnInit(): void {
      //  throw new Error('Method not implemented.');
      this.buildForm();
     }

     buildForm(){
      this.FormLogin = this.fb.group({
        username: [null, Validators.required],
        password: [null, Validators.required]
      })
     }

     login(){
         const input : AdminDTO ={
           username: this.FormLogin.controls['username'].value,
           password: this.FormLogin.controls['password'].value
         }
        
         this.loginService.login(input).subscribe(res =>{
          if(res.status == 200){
               this.tokenService.setName(res.data.fullName);
               this.tokenService.setRole(res.data.role);
               this.tokenService.setToken(res.data.token);
               this.router.navigate(['/user-management'])
          }
          
         })

     }
       
}
