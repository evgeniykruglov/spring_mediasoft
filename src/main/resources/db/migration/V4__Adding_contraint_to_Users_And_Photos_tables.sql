ALTER TABLE homework.users_photos
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES homework.user (id);

ALTER TABLE homework.users_photos
    ADD CONSTRAINT fk_photo
        FOREIGN KEY (photo_id)
            REFERENCES homework.photos (id);