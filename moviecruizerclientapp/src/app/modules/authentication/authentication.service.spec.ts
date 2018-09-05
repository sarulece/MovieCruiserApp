import { TestBed, inject, fakeAsync } from '@angular/core/testing';
import { HttpClientModule, HttpClient } from  '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';

const testConfig={
    registerUser: {
        positive: {
            firstName: 'test',
            lastName: 'test',
            userName: 'testUser',
            password: 'test123'
        }
    },
    loginUser: {
        positive: {
            firstName: '',
            lastName: '',
            userName: 'testUser',
            password: 'test123'
        }
    }

}
describe('AuthenticationService', () => {
    let authService : AuthenticationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [AuthenticationService]
    });
    authService = TestBed.get(AuthenticationService);
  });

  it('should be created', inject([AuthenticationService], (service: AuthenticationService) => {
    expect(service).toBeTruthy();
  }));

  it('should register user', fakeAsync(() => {

    let  data = testConfig.registerUser.positive;
    inject([AuthenticationService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(authService.authServiceEndPoint);
        expect(mockReq.request.url).toEqual(authService.authServiceEndPoint, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    authService.registerUser(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should login user', fakeAsync(() => {

    let  data = testConfig.loginUser.positive;
    inject([AuthenticationService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(authService.authServiceEndPoint);
        expect(mockReq.request.url).toEqual(authService.authServiceEndPoint, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    authService.loginUser(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));


});
