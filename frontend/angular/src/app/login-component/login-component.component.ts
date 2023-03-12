import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  user:User | undefined;

  constructor(
    private authService: AuthService, private tokenStorage: TokenStorageService,
    private router:Router
  ) {
    
   }
//[Validators.required,Validators.pattern(
  //'^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$'
  //)]
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      // this.roles = this.tokenStorage.getUser().roles;
    }
  }
  onSubmit(){
    const { username, password } = this.form;
    // this.user.username=username;
    // this.user.password=password;
    this.user=this.form;
    console.log(this.user);
    this.authService.login(this.form).subscribe(
      
      data => {
        console.log('the data here is ',data);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        // this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
    // if(!this.loginForm.valid){
    //   return;
    // }
    // localStorage.setItem('user',this.loginForm.value)
    // this.router.navigate(['/pageData'])
  }
  reloadPage(): void {
    this.router.navigate(['/pageData'])
  }
}
