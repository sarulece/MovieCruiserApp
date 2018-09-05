import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../user';
import { AuthenticationService } from '../../authentication.service';
import { AlertService } from '../../../../services/alert.service';


@Component({
  selector: 'auth-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  user : User;
  constructor(private authService: AuthenticationService, private router: Router, private alertService: AlertService) { 
    this.user = new User();
  }

  ngOnInit() {
  }

  registerUser()
  {
    console.log("new user: ", this.user);
    this.authService.registerUser(this.user).subscribe((data) => {
      console.log(" user data: ", data);
      this.router.navigate(["/login"]);
    },
    error => {
      this.alertService.error(error['error']);
    }
  ); 
  }

  resetUser()
  {
    this.user = new User();
  }

}
