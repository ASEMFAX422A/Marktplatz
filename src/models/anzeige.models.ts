export class AnzeigeDto {
  name: string;
  description: string;
  preis: number;
  image: string;

  constructor(name: string, description: string, preis: number, image:string) {
    this.name = name;
    this.image = image;
    this.description = description;
    this.preis = preis;
  }
}
