export interface DiaryRequestDto {

    title: string;
    content: string;
    userId:number;
    mood?: string;
    localtion?: string;
    weather?: string;
    tags?: string[];
    isFavorite?: boolean;
    isPrivate?: boolean;

}
