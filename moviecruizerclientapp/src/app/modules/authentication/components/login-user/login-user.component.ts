import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../user';
import { AuthenticationService } from '../../authentication.service';
import { AlertService } from '../../../../services/alert.service';

@Component({
  selector: 'auth-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  user : User;

  constructor(private authService: AuthenticationService, private router: Router, private alertService: AlertService) { 
    this.user = new User();
  }

  ngOnInit() {
  }

  loginUser()
  {
    console.log("login user: ", this.user);
    this.authService.loginUser(this.user).subscribe((data) => {
      console.log("response: " + data)
      if(data['token'])
      {
        console.log(" login token: ", data['token']);
        this.authService.setLoginToken(data['token']);
        this.router.navigate(['/movies/popular']);
      }      
    },
    error => {
      console.log("login user error: " + JSON.stringify(error));
      this.alertService.error(error['error']);
  }
  );   
  }
}
