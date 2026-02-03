export interface DiaryResponseDto {
      id: number;
  title: string;
  content: string;
  userId: number;
  mood?: string;
  location?: string;
  weather?: string;
  tags?: string[];
  isFavorite?: boolean;
  isPrivate?: boolean;
  createdAt: string;
  updatedAt: string;
}
