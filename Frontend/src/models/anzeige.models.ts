export class AnzeigeDto {
  id: number
  name: string;
  description: string;
  preis: number;
  image: string;

  constructor(id:number ,name: string, description: string, preis: number, image:string) {
    this.id=id;
    this.name = name;
    this.image = image;
    this.description = description;
    this.preis = preis;
  }
}
