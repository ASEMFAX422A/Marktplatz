export class UserDto {
 id: number;
 name: string;
 role: string;
 email: string;
 username: string;
 password: string;
 profilPic: string;

  constructor (id: number, name: string, role: string, email: string, username: string, password: string, profilPic: string){
    this.id = id;
    this.name = name;
    this.role = role;
    this.email = email;
    this.username = username;
    this.password = password;
    this.profilPic = profilPic;
  }
}
